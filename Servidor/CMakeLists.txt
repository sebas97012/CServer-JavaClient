cmake_minimum_required(VERSION 3.9)
project(Server )
include_directories(/home/sebas97012/Documents/Development/libs/ChSocket)
link_directories(/home/sebas97012/Documents/Development/libs/ChSocket)

set(CMAKE_C_STANDARD 99)

add_executable(Server main.c)
target_link_libraries(Server libChSocket.a)
