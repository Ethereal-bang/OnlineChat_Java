# User 用户

## /login 创建/登录账号
**Path**: int id, String password
**msg**: 
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

## /modify/{key}
**PathVariable**: name | word | avatar
**msg**: 修改成功 | 修改失败

# Contact 联系人
库(多对多): id, own, contact, score 亲密度

## /? 通过ID添加好友

## /? 删除联系人

## /? 屏蔽联系人

## /? 联系人列表
name, avatar, news, time, state
### ? 全部
### ? 按亲密度排行
### ? 检索

# News 消息
库(多对一): id, sender, receiver, content, word, time
+ ?content富文本,word纯文字便于词云

## /? 与某人聊天记录
