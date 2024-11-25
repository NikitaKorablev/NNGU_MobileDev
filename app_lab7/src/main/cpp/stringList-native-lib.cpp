#include <jni.h>
#include "StringList.h"
#include <string>

StringList *list = nullptr;

extern "C"
JNIEXPORT void JNICALL
Java_com_app_StringListActivity_createStringList(JNIEnv *env, jobject thiz) {
    if (list == nullptr) {
        list = new StringList();
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_StringListActivity_addString(JNIEnv *env, jobject thiz, jstring str) {
    const char *nativeString = env->GetStringUTFChars(str, 0);
    list->addString(std::string(nativeString));
    env->ReleaseStringUTFChars(str, nativeString);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_StringListActivity_removeLastString(JNIEnv *env, jobject thiz) {
    list->removeLastString();
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_app_StringListActivity_getFormattedStrings(JNIEnv *env, jobject thiz) {
    std::string formattedString = list->getFormattedString();
    return env->NewStringUTF(formattedString.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_StringListActivity_destroyStringList(JNIEnv *env, jobject thiz) {
    delete list;
}