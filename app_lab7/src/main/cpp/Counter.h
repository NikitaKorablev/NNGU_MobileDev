#ifndef COUNTER_H
#define COUNTER_H

class Counter {
public:
    Counter();
    [[nodiscard]] int get_count() const;
    void increment();
    void reset();

private:
    int count_;
};

class Calculator {
public:
    Calculator();
    [[nodiscard]] double get_v1() const;
    [[nodiscard]] double get_v2() const;
    void setValues(double v1, double v2);
    void add();
    void mul();
    void sub();
    void div();
private:
    double v1, v2;
};


#endif // COUNTER_H