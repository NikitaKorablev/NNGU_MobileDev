#include <jni.h>
#include "Counter.h"

Counter* counter = nullptr;
Calculator* calculator = nullptr;

extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_createCounter(JNIEnv *env, jobject thiz) {
    if (counter == nullptr) {
        counter = new Counter();
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_incrementCounter(JNIEnv *env, jobject thiz) {
    if (counter != nullptr) {
        counter->increment();
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_resetCounter(JNIEnv *env, jobject thiz) {
    if (counter != nullptr) {
        counter->reset();
    }
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_app_BaseTaskActivity_getCounterValue(JNIEnv *env, jobject thiz) {
    if (counter != nullptr) {
        return counter->get_count();
    }
    return 0;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_deleteCounter(JNIEnv *env, jobject thiz) {
    if(counter != nullptr)
        delete counter;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_createCalculator(JNIEnv *env, jobject thiz) {
    // TODO: implement createCalculator()
    if (!calculator)
        calculator = new Calculator();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_add(JNIEnv *env, jobject thiz) {
    // TODO: implement add()
    if (!calculator) return;
    calculator->add();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_mul(JNIEnv *env, jobject thiz) {
    // TODO: implement mul()
    if (!calculator) return;
    calculator->mul();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_sub(JNIEnv *env, jobject thiz) {
    // TODO: implement sub()
    if (!calculator) return;
    calculator->sub();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_div(JNIEnv *env, jobject thiz) {
    // TODO: implement div()
    if (!calculator) return;
    calculator->div();
}
extern "C"
JNIEXPORT jdouble JNICALL
Java_com_app_BaseTaskActivity_getV1(JNIEnv *env, jobject thiz) {
    // TODO: implement getV1()
    if (!calculator) return 0.0;
    return calculator->get_v1();
}
extern "C"
JNIEXPORT jdouble JNICALL
Java_com_app_BaseTaskActivity_getV2(JNIEnv *env, jobject thiz) {
    // TODO: implement getV2()
    if (!calculator) return 0.0;
    return calculator->get_v2();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_app_BaseTaskActivity_setVals(JNIEnv *env, jobject thiz, jdouble v1, jdouble v2) {
    if (!calculator) return;
    calculator->setValues(v1, v2);
}