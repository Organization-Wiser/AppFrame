#!/usr/bin/python
#-*-coding:utf-8-*-

#keystore信息
keystorePath = "./app/keystore/wiser.keystore"
keyAlias = "wiser"
keystorePassword = "wiser123455"
keyPassword = "wiser12345"

#加固后的源文件名（未重签名）
protectedSourceApkName = "app-release_reinforce.apk"
#加固后的源文件所在文件夹路径(...path),注意结尾不要带分隔符，默认在此文件夹根目录
protectedSourceApkDirPath = "./app/build/app"
#渠道包输出路径，默认在此文件夹Channels目录下
channelsOutputFilePath = "./app/build/app/channels"
#渠道名配置文件路径，默认在此文件夹根目录
channelFilePath = "./app/defense/channel/channellist"
#Android SDK buidtools path , please use above 27.0+
sdkBuildToolPath = "/Users/wiserwong/Library/Android/sdk/build-tools/27.0.2" #本地
#sdkBuildToolPath = "/home/android-sdk/build-tools/27.0.0" #jenkins