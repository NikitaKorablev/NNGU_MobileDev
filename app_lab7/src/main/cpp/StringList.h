//
// Created by Suren on 09.10.2024.
//

#ifndef MY_APPLICATION_STRINGLIST_H
#define MY_APPLICATION_STRINGLIST_H

#include <vector>
#include <string>
#include <sstream>
#include <algorithm>

class StringList {
private:
    std::vector<std::string> strings;

public:
    void addString(const std::string& str) {
        strings.push_back(str);
    }

    void removeLastString() {
        if (!strings.empty()) {
            strings.pop_back();
        }
    }

    std::string getFormattedString() const {
        std::ostringstream oss;
        for (size_t i = 0; i < strings.size(); ++i) {
            std::string capitalized = strings[i];
            if (!capitalized.empty()) {
                capitalized[0] = toupper(capitalized[0]);
            }
            oss << capitalized;
            if (i < strings.size() - 1) {
                oss << ", ";
            }
        }
        return oss.str();
    }
};


#endif //MY_APPLICATION_STRINGLIST_H
