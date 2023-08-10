```java
private String[] getFieldsName(String class_name, String method_name) throws Exception {
Class> clazz = Class.forName(class_name);
String clazz_name = clazz.getName();
ClassPool pool = ClassPool.getDefault();
ClassClassPath classPath = new ClassClassPath(clazz);
pool.insertClassPath(classPath);
CtClass ctClass = pool.get(clazz_name);
CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
MethodInfo methodInfo = ctMethod.getMethodInfo();
CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
if(attr == null){
return null;
}
String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
// 如果是静态方法，则第一就是参数
// 如果不是静态方法，则第一个是"this"，然后才是方法的参数
// 我接口中没有写public修饰词，导致我的数组少一位参数，所以再往后一位，原本应该是 XX ? 0 : 1
int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 1 : 2;
for (int i=0;i
paramsArgsName[i] = attr.variableName(i+pos);
}
return paramsArgsName;

}

```

