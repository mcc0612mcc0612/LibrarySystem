# 数据库实验报告

## ER图

<embed src="https://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/31181401/share/2023-6-5/1685975939/main.svg" width="800" height="800" type="image/svg+xml">

## 数据库表

#### book_info

| 变量名         | 类型        |
| -------------- | ----------- |
| <u>book_id</u> | int         |
| name           | varchar(20) |
| author         | varchar(20) |
| publish        | varchar(20) |
| ISBN           | varchar(15) |
| Introduction   | text        |
| language       | varchar(4)  |
| price          | decimal     |
| pub_date       | date        |
| class_id       | int         |
| status         | int         |



#### Read_info

| 变量名           | 类型        |
| ---------------- | ----------- |
| <u>reader_id</u> | int         |
| name             | varchar(10) |
| sex              | varchar(2)  |
| birth            | date        |
| address          | varchar(50) |
| phone            | varchar(15) |

#### Reader_account

| 变量名           | 类型        |
| ---------------- | ----------- |
| <u>reader_id</u> | int         |
| username         | varchar(20) |
| password         | varchar(20) |
| borrow_times     | int         |
| reserve_times    | int         |

#### Admin_account

| 变量名          | 类型        |
| --------------- | ----------- |
| <u>admin_id</u> | int         |
| username        | varchar(20) |
| password        | varchar(20) |

#### lend表

| 变量名         | 类型 |
| -------------- | ---- |
| <u>lend_id</u> | int  |
| Reader_id      | int  |
| book_id        | int  |
| lend_date      | date |
| back_date      | date |

#### Reserve表

| 变量名            | 类型 |
| ----------------- | ---- |
| <u>Reserve_id</u> | int  |
| Reader_id         | int  |
| book_id           | int  |
| Reserve_time      | date |
| Get_time          | date |

## 触发器

1. 归还图书

   ```sql
   CREATE TRIGGER reserve_book
   AFTER INSERT ON reserve_list
   FOR EACH ROW
   BEGIN
       DECLARE book_stat INT;
       SELECT status INTO book_stat FROM book_info WHERE book_id = NEW.book_id;
   
       UPDATE reader_card SET reserve_time = reserve_time + 1 WHERE reader_id = NEW.reader_id;
   
       IF book_stat = 0 THEN
           UPDATE book_info SET status = 2 WHERE book_id = NEW.book_id;
       END IF;
   END;
   
   
   ```

2. 在预约情况下借阅图书

   ```mysql
   CREATE TRIGGER take_book
   AFTER UPDATE ON reserve_list
   FOR EACH ROW
   BEGIN
       DECLARE is_reserved INT;
       SELECT COUNT(*) INTO is_reserved FROM reserve_list WHERE book_id = OLD.book_id;
       IF is_reserved = 0 THEN
           UPDATE book_info SET status = 0 WHERE book_id = NEW.book_id;
       ELSE 
           UPDATE book_info SET status = 2 WHERE book_id = NEW.book_id;
       END IF;
   END;
   
   ```

3. 删除图书

   ```
   CREATE TRIGGER BookDelete
   AFTER DELETE ON book_info
   FOR EACH ROW
   BEGIN
       DELETE FROM reserve_list where book_id = OLD.book_id;
       DELETE FROM lend_list where book_id = OLD.book_id;
   END;
   ```

## 数据库架构

**MVC三层架构**

**文件树**

```
└─demo
    ├─src
    │  └─main
    │      └─webapp
    │          ├─img
    │          ├─library
    │          │  ├─bean
    │          │  ├─controller
    │          │  ├─dao
    │          │  └─service
    │          └─WEB-INF
    │              └─jsp
```



### 前端:jsp

网页代码

### 业务逻辑层：controller

用于接收用户提交请求的代码。

### 服务层：service

处理业务逻辑

### 持久层：dao

直接与数据库交互

## 实现功能

### 登录

![image-20230704132211120](assets\image-20230704132211120.png)

## 管理员账号

![image-20230704135145819](assets\image-20230704135145819.png)

### 图书信息

删除，编辑，预约

![image-20230704135208182](assets\image-20230704135208182.png)

![image-20230704135240246](assets\image-20230704135240246.png)

### 图书增添

![image-20230704135326271](assets\image-20230704135326271.png)

### 读者信息

![image-20230704135411893](assets\image-20230704135411893.png)

### 读者增添

![image-20230704135506163](.\assets\image-20230704135506163.png)

### 借还管理

![image-20230704135552574](.\assets\image-20230704135552574.png)

## 读者账号

![image-20230704135630811](.\assets\image-20230704135630811.png)

### 图书借阅，预约，归还

![image-20230704135648919](.\assets\image-20230704135648919.png)

### 图书查询

![image-20230704135720923](.\assets\image-20230704135720923.png)

## 图书借阅上限约束

![image-20230704135751402](.\assets\image-20230704135751402.png)

### 个人信息

![image-20230704135816339](.\assets\image-20230704135816339.png)

### 借还日志

![image-20230704135834205](.\assets\image-20230704135834205.png)

## 实验贡献

All by 毛陈诚
