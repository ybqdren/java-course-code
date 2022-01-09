#!/usr/bin/env bash

pid=`ps -ef | grep imooc-springboot-study.jar | grep -v grep | awk '{print $2}'`

if [[ -z "${pid}" ]]
then
    echo application is already stopped
else
    echo kill ${pid}
    kill -9 ${pid}
fi
