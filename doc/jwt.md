## JWT RSA非对称加密 无状态登录

### 登录鉴权中心
* [RASUtils](/redscarf-common/src/main/java/com/baiye/redscarf/common/util/RSAUtils.java)，jwt获取以及处理
* 切面注解[@ApiAuth](/redscarf-common/src/main/java/com/baiye/redscarf/common/anno/ApiAuth.java)
* 切点处理[JWTAuth](/redscarf-common/src/main/java/com/baiye/redscarf/common/auc/JWTAuth.java)，获取jwt以及校验jwt并获取AuthID.

### 使用
* 需要授权的接口，在该方法上添加[@ApiAuth](/redscarf-common/src/main/java/com/baiye/redscarf/common/anno/ApiAuth.java)注解，并且传递的参数必须继承[AuthID](/redscarf-common/src/main/java/com/baiye/redscarf/common/model/base/AuthID.java)
* 通过切面，会自动校验jwt有效性

### RSA私钥公钥获取
* openssl genrsa -out rsa_private_key.pem 1024
* openssl pkcs8 -topk8 -inform PEM -in rsa_private_key.pem -outform PEM -nocrypt
*  openssl rsa -in rsa_private_key.pem -out rsa_public_key.pem -pubout