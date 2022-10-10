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
**Param**: id, val
**msg**: 修改成功 | 修改失败
+ To fix: ws 通知前端更新

# ContactItem 联系人
库(多对多): id, own, contact, score 亲密度, state, news, time, read
+ news, time 最后一次消息和时间
+ read <span style="color:orange">对方</span>是否已读消息
+ 每对关系有两条记录
+ state(本人主动) 0申请中 | 1已同意 | 2已拒绝 | 3已屏蔽
+ state(本人收到) 4待处理
+ state(不存在于数据库,方便前端展示) -1 未申请

## /search/{keyword} 搜索用户
+ Param: id 用户 ID
+ PathVariable: 为数字时查询该 ID 用户；不然在用户好友列表中查询昵称
+ data
  + user 查询 ID (ContactItem)
  + list 查询好友 (ContactItem[])

## /add 通过ID添加好友
+ Param: id, contact
+ msg: 已发送好友申请 | 发送好友申请失败

## /getAddList/{id} 好友申请列表(他人的申请)
+ PathVariable: id
+ data-list: ContactItem[]

## /handleApplication 同意/拒绝好友申请
+ Param: id, contact, state: 1 | 2

## /? 删除联系人

## /? 屏蔽联系人

## /list/{id} 联系人列表/消息列表
+ PathVariable: id
+ data-list: ContactItem[]

### ? 全部
### ? 按亲密度排行
### ? 检索

# News 消息
库(多对一): id, sender, receiver, content, word, time
+ ?content富文本,word纯文字便于词云

## /?list 消息列表

## /getDialogue 与某人聊天记录
并将对方 read 置为 true
+ Params: own, contact
+ data: {list: News[], user: User}

## /send 发送消息
新增到 News，并更新 Contact news, time 字段
+ RequestBody: News(sender, receiver, content, word)
+ data-id: 该消息记录id

# /images 图片

## /upload 上传
+ POST
+ RequestParam: file
+ data-path: 图片访问路径

## /{图片名} 请求文件

## /? 上传表情包

## /? 用户的表情包

# Websocket
**事件类型：**
news: 新消息
application: 好友申请 