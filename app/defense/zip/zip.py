#!/usr/bin/python
# coding=utf-8
# noinspection PyUnresolvedReferences
import sys
# noinspection PyUnresolvedReferences
import zipfile
# noinspection PyUnresolvedReferences
import shutil
# noinspection PyUnresolvedReferences
import os

# zip压缩文件方法
def zip_dir(dirname,zipfilename):
    filelist = []
    if os.path.isfile(dirname):
        filelist.append(dirname)
    else :
        for root, dirs, files in os.walk(dirname):
            for name in files:
                filelist.append(os.path.join(root, name))
    zf = zipfile.ZipFile(zipfilename, "w", zipfile.zlib.DEFLATED)
    for tar in filelist:
        arcname = tar[len(dirname):]
        #print arcname
        zf.write(tar,arcname)
    zf.close()
#执行
zip_dir('./app/build/app/','./app/build/app/apkZip.zip')