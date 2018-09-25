# 本地渠道打包流程

### 除了应用宝生成所有渠道
 - 第一步 执行gradle命令 : gradle clean assembleRelease  (app/build/app/app-release_reinforce.apk)
 - 第二步 执行python命令 : python /Users/wiserwong/androidPjt/MFrame/app/defense/channel/ApkResigner.py
    
### 应用宝渠道
 - 第一步 下载应用宝加固工具，把app/build/app/app-release.apk加固(不要自动签名)
 - 第二步 拷贝文件 app-release_legu.apk 到 app/build/app/ 目录下
 - 第三步 执行python命令 : python /Users/wiserwong/androidPjt/MFrame/app/defense/channel/ApkResigner-yyb.py