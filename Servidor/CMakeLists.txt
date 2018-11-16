cmake_minimum_required(VERSION 3.9)
project(Server )
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/ChSocket)
link_directories(${CMAKE_CURRENT_SOURCE_DIR}/ChSocket)

set(CMAKE_C_STANDARD 99)

add_executable(Server main.c)
target_link_libraries(Server libChSocket.a)
