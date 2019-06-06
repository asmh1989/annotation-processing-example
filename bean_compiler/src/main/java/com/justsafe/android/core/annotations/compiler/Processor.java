package com.justsafe.android.core.annotations.compiler;

import com.justsafe.android.core.annotations.Bean;
import com.justsafe.android.core.annotations.BeanImp;
import com.justsafe.android.core.annotations.ImpBean;
import com.justsafe.android.core.annotations.Keep;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 预处理, 生成代码
 *
 * @author sun
 */
public class Processor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {

            // write the defines class to a java file
            try {
                // find all the classes that uses the supported annotations
                Set<TypeElement> typeElements =
                        ProcessingUtils.getTypeElementsToProcess(
                                roundEnv.getRootElements(), annotations);

                TypeElement manager =
                        ProcessingUtils.getTypeElementsToProcess(roundEnv.getRootElements());

                if (manager == null) {
                    return false;
                }
                String packageName =
                        elementUtils.getPackageOf(manager).getQualifiedName().toString();
                ;
                String typeName = manager.getSimpleName().toString();

                ClassName className = ClassName.get(packageName, typeName);

                ClassName generatedClassName =
                        ClassName.get(packageName, NameStore.getGeneratedClassName(typeName));

                // define the wrapper class
                TypeSpec.Builder classBuilder =
                        TypeSpec.classBuilder(generatedClassName)
                                .addModifiers(Modifier.PUBLIC)
                                .addAnnotation(Keep.class);

                // add constructor
                classBuilder.addMethod(
                        MethodSpec.constructorBuilder()
                                .addModifiers(Modifier.PUBLIC)
                                .addParameter(className, NameStore.Variable.ANNOTATION_MANAGER)
                                .addStatement(
                                        "$N($N)",
                                        NameStore.Method.ADD_BEAN,
                                        NameStore.Variable.ANNOTATION_MANAGER)
                                .addStatement(
                                        "$N($N)",
                                        NameStore.Method.ADD_BEAN_IMP,
                                        NameStore.Variable.ANNOTATION_MANAGER)
                                .build());

                // add method that maps the views with id
                MethodSpec.Builder beanMethodBuilder =
                        MethodSpec.methodBuilder(NameStore.Method.ADD_BEAN)
                                .addModifiers(Modifier.PRIVATE)
                                .returns(void.class)
                                .addParameter(className, NameStore.Variable.ANNOTATION_MANAGER);

                MethodSpec.Builder beanImpMethodBuilder =
                        MethodSpec.methodBuilder(NameStore.Method.ADD_BEAN_IMP)
                                .addModifiers(Modifier.PRIVATE)
                                .returns(void.class)
                                .addParameter(
                                        className,
                                        NameStore.Variable.ANNOTATION_MANAGER,
                                        Modifier.FINAL);

                // for each such class create a wrapper class for binding

                int i = 0;
                for (TypeElement typeElement : typeElements) {
                    Bean bean = typeElement.getAnnotation(Bean.class);

                    if (bean != null) {

                        String name = bean.name();
                        if (name.isEmpty()) {
                            name = typeElement.getQualifiedName().toString();
                        }

                        //     manager.addBean(IBase.class,
                        // "com.mindorks.annotation.processing.example.IBase");
                        beanMethodBuilder.addStatement(
                                "$N.addBean($T.class, $S)",
                                NameStore.Variable.ANNOTATION_MANAGER,
                                typeElement,
                                name);
                    }

                    BeanImp beanImp = typeElement.getAnnotation(BeanImp.class);

                    if (beanImp != null) {

                        String[] brand = beanImp.brand();
                        int maxVersion = beanImp.maxVersion();
                        int minVersion = beanImp.minVersion();
                        String[] model = beanImp.model();
                        int priority = beanImp.priority();
                        String[] rom = beanImp.rom();
                        String tag = beanImp.tag();
                        int[] versions = beanImp.versions();

                        String beanName = "bean_" + i;
                        // ImpBean bean = new ImpBean();
                        beanImpMethodBuilder.addStatement(
                                "$1N $2N = new $1N()", ImpBean.class.getName(), beanName);
                        if (brand.length > 0) {
                            beanImpMethodBuilder.addStatement(stringArrayBlock("brand", brand));
                            beanImpMethodBuilder.addStatement("$1N.brand = brand", beanName);
                        }

                        if (model.length > 0) {
                            beanImpMethodBuilder.addStatement(stringArrayBlock("model", model));
                            beanImpMethodBuilder.addStatement("$1N.model = model", beanName);
                        }

                        if (rom.length > 0) {
                            beanImpMethodBuilder.addStatement(stringArrayBlock("rom", rom));
                            beanImpMethodBuilder.addStatement("$1N.rom = rom", beanName);
                        }

                        if (versions.length > 0) {
                            beanImpMethodBuilder.addStatement(intArrayBlock("versions", versions));
                            beanImpMethodBuilder.addStatement("$1N.versions = versions", beanName);
                        }

                        beanImpMethodBuilder.addStatement(
                                "$1N.maxVersion = $2L", beanName, maxVersion);
                        beanImpMethodBuilder.addStatement(
                                "$1N.minVersion = $2L", beanName, minVersion);
                        beanImpMethodBuilder.addStatement("$1N.priority = $2L", beanName, priority);
                        beanImpMethodBuilder.addStatement("$1N.tag = $2S", beanName, tag);

                        beanImpMethodBuilder.addStatement(
                                "$1N.addBeanImp($2T.class, $3N)",
                                NameStore.Variable.ANNOTATION_MANAGER,
                                typeElement,
                                beanName);
                    }

                    i++;
                }

                classBuilder.addMethod(beanMethodBuilder.build());
                classBuilder.addMethod(beanImpMethodBuilder.build());

                JavaFile.builder(packageName, classBuilder.build()).build().writeTo(filer);
            } catch (Exception e) {
                messager.printMessage(Diagnostic.Kind.ERROR, e.toString());
            }
        }
        return true;
    }

    CodeBlock stringArrayBlock(String name, String[] arr) {
        String literal = "{\"" + String.join("\",\"", arr) + "\"}";
        ArrayTypeName stringArray = ArrayTypeName.of(String.class);
        return CodeBlock.builder().add("$1T $3N = new $1T $2L", stringArray, literal, name).build();
    }

    CodeBlock intArrayBlock(String name, int[] arr) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(arr[i]);
        }
        builder.append("}");

        return CodeBlock.builder().add("int[] $N = $L", name, builder.toString()).build();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new TreeSet<>(
                Arrays.asList(
                        Bean.class.getCanonicalName(),
                        BeanImp.class.getCanonicalName(),
                        Keep.class.getCanonicalName()));
    }
}
