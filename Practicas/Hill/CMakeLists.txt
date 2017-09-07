cmake_minimum_required(VERSION 3.8)
project(Hill)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c cmake-build-debug/Pixel.c cmake-build-debug/Pixel.h)
add_executable(Hill ${SOURCE_FILES})