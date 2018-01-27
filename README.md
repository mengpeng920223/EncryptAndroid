# EncryptAndroid

# 封装安卓常见的加密方式
- **RSA加密，解密**
- **DES加密，解密**
- **MD5加密**
- **SHA1加密**
- **Base64加密，解密**
- **Base64字符串转成图片**
- **图片转成Base64字符串**


## RSA加密，解密

step1:分别获取公钥私钥
```
    KeyPair keyPair = RsaEncryptUtils.createRsaPassword();
    PrivateKey aPrivate = keyPair.getPrivate();
    PublicKey aPublic = keyPair.getPublic();

```
step2:加密
```
    //一般情况下采用私钥进行加密
    String encode_result = RsaEncryptUtils.rsaEncode(content, aPrivate);
```
step3:解密
```
    //一般情况下采用私钥进行加密
    String decode_result = RsaEncryptUtils.rsaDecode(str, aPublic);
```

## DES加密，解密

step1:加密
```
    //自己定义的加密的密码，需要加密的内容
    String encode = DesEncryptUtils.desEncode(passwords, content);
```
step2:解密
```
    //加密后的内容 ， 加密时自定义的密码
    String des_result = DesEncryptUtils.desDecode(decode, passwords);
```

## MD5加密

step:加密
```
    //需要加密的内容
    String encode = Md5EncryptUtils.md5Encode(content);
```

## SHA1加密

step:加密
```
    //需要加密的内容
    String encode = Sha1EncryptUtils.sha1Encode(content);
```

## Base64加密，解密

step1:加密
```
    //需要加密的内容
    String encode = Base64.encode(content);
```
step2:解密
```
    //加密后的内容
    String content = Base64.decode(encode);
```
## Base64字符串转成图片

step: base64字符串转化成图片
```
   /**
    * 参数一：需要转成图片的字符串 图片名称 
    * 参数二：图片路径，路径为空时默认为
         Environment.getExternalStoragePublicDirectory(
         Environment.DIRECTORY_DCIM) + filename
    * 参数三：图片的名称(例如:abc.png , aaa.jpg)
    */
    String encode = Base64.stringToPic(content , filepath , filename);
```

## 图片转化成base64字符串
step:图片转化成base64字符串
```
    String content = Base64.picToString(filePath);
```

