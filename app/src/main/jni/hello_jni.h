#include <jni.h>

#ifndef HELLOJNI_HELLO_JNI_H
#define HELLOJNI_HELLO_JNI_H

JNIEXPORT jstring JNICALL
Java_com_hxj_hellojni_MainActivity_messageFromJNI(JNIEnv *env, jobject instance);

#endif //HELLOJNI_HELLO_JNI_H