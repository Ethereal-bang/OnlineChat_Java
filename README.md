# User

## /login 创建/登录账号

+ 创建成功
+ 登陆成功
+ 创建失败
+ 密码错误

## /getInfo/{id} 返回用户信息

```json
{
  "flag": true,
  "msg": "查询成功",
  "data": {
    "user": {
      "id": 939847757,
      "name": "用户名",
      "avatar": "http://photo.chaoxing.com/photo_80.jpg",
      "word": "用户很懒，什么都没有留下",
      "password": null
    }
  }
}
```
