# 超市管理系统

这个是学习完Java基础后的一个练习，有以下功能：
- 商品类型管理
- 商品管理
- 会员管理
- 购买管理
- 订单查询
- 排行统计
- 角色管理

------------

## 1.商品类型管理
管理商品类型信息；商品类型管理拥有添加、删除、修改、查询商品类型功能（管理员权限）
## 2.商品管理
管理商品信息；商品管理拥有添加、删除、修改、查询商品功能（管理员权限）
## 3.会员管理
管理会员基本信息，会员管理拥有添加会员、修改会员、删除会员、查询会员、会员充值功能（管理员权限）
## 4.购买管理
输入商品ID-->显示商品详情-->输入购买数量-->输入下一个商品ID，如此反复。
  直到按下“确定购买”按钮。
  	确认购买后：提示“是否用会员卡支付”？如果是，输入会员卡号，并扣除金额。如果不是，则提示现金支付。
  	每次购买都生成简要订单记录(购买时间，总金额)，然后根据订单记录返回的新增订单ID 生成详细订单记录(每种商品的购买数量、单价)。
## 5.订单查询
输入商品编号或是会员编号，在控制台显示简要订单记录及详细订单记录
## 6.排行统计
按月份及按商品类别统计销量前10的商品及总销量。（收银员权限）
## 7.角色管理
共有管理员和收银员2个角色。管理员做商品类型管理、商品管理、会员管理，收银员做购买管理、订单查询和排行统计。


------------

## 使用方法：
启动 src\com\ming\MainProgram\MainProgram.java，启动即可
