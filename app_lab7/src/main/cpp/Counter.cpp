#include "Counter.h"

Counter::Counter() : count_(0) {}

int Counter::get_count() const {
    return count_;
}

void Counter::increment() {
    ++count_;
}

void Counter::reset() {
    count_ = 0;
}

Calculator::Calculator() : v1(0), v2(0) {}

double Calculator::get_v1() const { return v1; }
double Calculator::get_v2() const { return v2; }

void Calculator::add() {v1 = v1 + v2; v2 = 0;}
void Calculator::sub() {v1 = v1 - v2; v2 = 0;}
void Calculator::mul() {v1 = v1 * v2; v2 = 0;}
void Calculator::div() {v1 = v1 / v2; v2 = 0;}

void Calculator::setValues(double v1, double v2) {
    this->v1 = v1;
    this->v2 = v2;
}
