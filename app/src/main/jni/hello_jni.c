#include "hello_jni.h"

jstring Java_com_hxj_hellojni_MainActivity_messageFromJNI(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "Hello Java. I come from JNI.");
}