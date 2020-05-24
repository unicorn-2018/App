-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cdb1
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `code_tb`
--

DROP TABLE IF EXISTS `code_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `code_tb` (
  `code_id` varchar(20) NOT NULL COMMENT 'コードID',
  `code` varchar(20) NOT NULL COMMENT 'コード',
  `naiyo_1` varchar(20) NOT NULL COMMENT '内容１',
  `naiyo_2` varchar(20) DEFAULT NULL COMMENT '内容２',
  `biko` varchar(100) DEFAULT NULL COMMENT '備考',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`code_id`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='コードテーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_tb`
--

LOCK TABLES `code_tb` WRITE;
/*!40000 ALTER TABLE `code_tb` DISABLE KEYS */;
INSERT INTO `code_tb` VALUES ('A_001','1','全体',NULL,'ビルディング種類','2020-03-27 20:22:35','2020-03-27 20:22:35'),('A_001','2','チーム',NULL,'ビルディング種類','2020-03-27 20:22:35','2020-03-27 20:22:35'),('A_001','3','ユーザー',NULL,'ビルディング種類','2020-03-27 20:22:35','2020-03-27 20:22:35'),('A_002','0','#f6c2c2','祝日','親リソースID（イベント分類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('A_002','1','#FFD700','イベント','親リソースID（イベント分類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('A_002','2','#66CDAA','タスク','親リソースID（イベント分類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('A_002','3','#AFEEEE','誕生日','親リソースID（イベント分類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_001','1','#FA8072','チーム会','子リソースID（イベント種類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_001','2','#BC8F8F','目標面談','子リソースID（イベント種類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_001','3','#00BFFF','全体MTG','子リソースID（イベント種類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_001','99','#FFD700','未設定','子リソースID（イベント種類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_002','1','fa fa-calendar-check','重要','イベントアイコン','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_002','2','fa fa-coffee','遊び','イベントアイコン','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_002','3','fa fa-users','会議','イベントアイコン','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_002','4','far fas fa-edit','イベント','イベントアイコン','2020-03-27 20:22:35','2020-03-27 20:22:35'),('E_002','99','0','未設定','イベントアイコン','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_001','1','未着手',NULL,'タスク状況','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_001','2','進行中',NULL,'タスク状況','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_001','3','完了',NULL,'タスク状況','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_001','4','保留',NULL,'タスク状況','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_001','99','未設定',NULL,'タスク状況','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_002','1','#D8BfD8','目標設定','子リソースID（タスク種類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35'),('T_002','99','#66CDAA','未設定','子リソースID（タスク種類（色コード））','2020-03-27 20:22:35','2020-03-27 20:22:35');
/*!40000 ALTER TABLE `code_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_tb`
--

DROP TABLE IF EXISTS `event_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `event_tb` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'イベントID',
  `event_name` varchar(30) NOT NULL COMMENT 'イベント名',
  `event_start` varchar(16) NOT NULL COMMENT 'イベント開始日時',
  `event_end` varchar(16) DEFAULT NULL COMMENT 'イベント終了日時',
  `description` varchar(300) DEFAULT NULL COMMENT 'コメント',
  `event_icon_id` int(11) DEFAULT NULL COMMENT 'イベントアイコンID',
  `allday_flag` tinyint(2) DEFAULT '0' COMMENT '終日フラグ',
  `edit_auth_id` tinyint(2) NOT NULL COMMENT '編集権限ID',
  `task_flag` tinyint(2) DEFAULT '0' COMMENT 'タスクフラグ',
  `task_status_id` int(11) DEFAULT NULL COMMENT 'タスク状況ID',
  `event_vaild_f` tinyint(2) DEFAULT '1' COMMENT 'イベント有効フラグ',
  `event_version` int(11) DEFAULT '1' COMMENT 'イベントバージョン',
  `create_user_id` int(11) NOT NULL COMMENT '登録者ID',
  `update_user_id` int(11) NOT NULL COMMENT '更新者ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='イベントテーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_tb`
--

LOCK TABLES `event_tb` WRITE;
/*!40000 ALTER TABLE `event_tb` DISABLE KEYS */;
INSERT INTO `event_tb` VALUES (0,'ダミー','0000-00-00T00:00','9999-99-99T00:00','',NULL,NULL,0,NULL,NULL,NULL,NULL,0,0,'2018-11-17 13:44:00','2020-05-23 15:22:34'),(4,'タスクタイトル４','2019-10-12T00:00','2019-10-12T00:00','ここにコメントがはいります。ここにコメントがはいります。ここにコメントがはいります。ここにコメントがはいります。ここにコメントがはいります。ここにコメントがはいります。',NULL,1,1,1,1,1,12,1,1,'2018-11-17 13:44:00','2020-05-23 15:22:34'),(91,'タイトル','2020-05-09T00:00','2020-05-10T00:00','',NULL,1,1,0,NULL,1,1,2,1,'2020-05-17 18:32:06','2020-05-17 18:32:06'),(118,'タイトル56789012345678901234567890','2019-10-01T00:00','2019-10-01T00:00','123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890',NULL,1,1,0,NULL,1,5,1,1,'2020-05-17 18:26:32','2020-05-23 01:27:55'),(119,'タイトル','2019-10-01T00:00','2019-10-01T00:00','',NULL,1,1,1,1,1,1,1,1,'2020-05-17 18:32:06','2020-05-17 18:32:06'),(120,'タイトル','2019-10-01T00:00','2019-10-01T00:00','',NULL,1,1,1,1,1,1,1,1,'2020-05-17 18:32:49','2020-05-17 18:32:49'),(121,'タイトル','2019-10-01T00:00','2019-10-01T00:00','',NULL,1,1,1,1,1,1,1,1,'2020-05-17 18:35:01','2020-05-17 18:35:01'),(122,'タイトル','2019-10-01T00:00','2019-10-01T00:00','',NULL,1,1,1,1,1,2,1,1,'2020-05-17 18:37:25','2020-05-17 18:39:25'),(132,'目標設定タスク','2020-05-26T00:00','2020-05-26T00:00','課題の証跡を用意',NULL,1,1,1,NULL,1,8,1,1,'2020-05-17 19:22:07','2020-05-23 20:41:24'),(134,'タイトル','2019-10-01T00:00','2019-10-01T00:00','',NULL,1,1,1,3,1,2,1,1,'2020-05-17 21:38:35','2020-05-23 01:23:36'),(135,'チームイベント','2020-05-10T00:00','2020-05-10T00:00','テストコメントです。',3,1,0,0,NULL,1,14,1,1,'2020-05-17 21:50:57','2020-05-23 12:46:23'),(137,'タイトル','2020-05-23T00:00','2020-06-01T00:00','',1,1,1,0,NULL,1,10,1,1,'2020-05-23 14:50:39','2020-05-24 15:29:21'),(138,'タイトル','2020-05-23T00:00','2020-05-23T00:00','',NULL,1,0,0,NULL,1,1,1,1,'2020-05-23 20:36:29','2020-05-23 20:36:29'),(140,'全体MTG','2020-09-12T00:00','2020-09-12T00:00','',NULL,1,0,0,NULL,1,1,1,1,'2020-05-25 01:52:53','2020-05-25 01:52:53');
/*!40000 ALTER TABLE `event_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_tb`
--

DROP TABLE IF EXISTS `group_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_tb` (
  `group_id` int(11) NOT NULL COMMENT 'グループID',
  `group_name` varchar(20) DEFAULT NULL COMMENT 'グループ名',
  `group_type` tinyint(2) DEFAULT '1' COMMENT 'グループ種類',
  `group_vaild_f` tinyint(2) DEFAULT '1' COMMENT 'グループ有効フラグ',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='グループテーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_tb`
--

LOCK TABLES `group_tb` WRITE;
/*!40000 ALTER TABLE `group_tb` DISABLE KEYS */;
INSERT INTO `group_tb` VALUES (0,'テストグループ',0,1,'2018-11-17 13:44:34','2018-11-17 13:44:34'),(1,'1.Redチーム',0,1,'2018-11-17 13:44:34','2018-11-17 13:44:34'),(2,'2.Blueチーム',0,1,'2018-11-17 13:44:34','2018-11-17 13:44:34'),(3,'3.Greenチーム',0,1,'2018-11-17 13:44:34','2018-11-17 13:44:34'),(4,'4.ひよこ組',0,1,'2018-11-17 13:44:34','2018-11-17 13:44:34'),(5,'テスト無効フラグ',0,0,'2018-11-17 13:44:34','2018-11-17 13:44:34');
/*!40000 ALTER TABLE `group_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_tb`
--

DROP TABLE IF EXISTS `resource_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `resource_tb` (
  `event_id` int(11) NOT NULL COMMENT 'イベントID',
  `building_type_id` int(11) NOT NULL COMMENT 'ビルディング種類ID',
  `building_id` int(11) NOT NULL COMMENT 'ビルディングID',
  `parent_resource_type_id` varchar(11) NOT NULL COMMENT '親リソース種類ID',
  `child_resource_type_id` varchar(11) DEFAULT NULL COMMENT '子リソース種類ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`event_id`,`building_type_id`,`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='リソーステーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_tb`
--

LOCK TABLES `resource_tb` WRITE;
/*!40000 ALTER TABLE `resource_tb` DISABLE KEYS */;
INSERT INTO `resource_tb` VALUES (0,1,0,'0',NULL,'2020-05-23 15:22:34','2020-05-23 15:22:34'),(0,1,1,'3',NULL,'2020-05-23 15:22:34','2020-05-23 15:22:34'),(3,2,1,'1','1','2020-03-27 23:01:00','2020-03-27 23:01:00'),(4,3,1,'2',NULL,'2020-05-23 15:22:34','2020-05-23 15:22:34'),(91,3,1,'1','1','2020-05-17 02:20:05','2020-05-17 02:20:05'),(91,3,2,'1','1','2020-05-17 02:20:05','2020-05-17 02:20:05'),(116,3,1,'1','1','2020-05-17 18:04:10','2020-05-17 18:04:10'),(118,1,1,'1','1','2020-05-23 01:27:55','2020-05-23 01:27:55'),(118,3,1,'1','1','2020-05-23 01:27:55','2020-05-23 01:27:55'),(132,3,1,'2','1','2020-05-23 20:41:24','2020-05-23 20:41:24'),(134,3,1,'2',NULL,'2020-05-23 01:23:36','2020-05-23 01:23:36'),(135,2,0,'1',NULL,'2020-05-23 12:46:23','2020-05-23 12:46:23'),(135,2,1,'1',NULL,'2020-05-23 12:46:23','2020-05-23 12:46:23'),(137,2,0,'1',NULL,'2020-05-24 15:29:22','2020-05-24 15:29:22'),(137,3,1,'1',NULL,'2020-05-24 15:29:22','2020-05-24 15:29:22'),(138,3,1,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,5,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,6,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,9,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,10,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,13,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,14,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(138,3,18,'1',NULL,'2020-05-23 20:36:30','2020-05-23 20:36:30'),(140,3,1,'1','3','2020-05-25 01:52:53','2020-05-25 01:52:53');
/*!40000 ALTER TABLE `resource_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence_table`
--

DROP TABLE IF EXISTS `sequence_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sequence_table` (
  `sequence_name` bigint(20) NOT NULL COMMENT '連番名',
  `sequence_id` bigint(20) NOT NULL COMMENT '連番ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`sequence_name`,`sequence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='採番テーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence_table`
--

LOCK TABLES `sequence_table` WRITE;
/*!40000 ALTER TABLE `sequence_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `sequence_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tb`
--

DROP TABLE IF EXISTS `user_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_tb` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ユーザーID',
  `user_email` varchar(50) DEFAULT NULL COMMENT 'メールアドレス',
  `user_pass` varchar(50) DEFAULT NULL COMMENT 'パスワード',
  `name` varchar(50) DEFAULT NULL COMMENT '名前',
  `employee_number` varchar(50) DEFAULT NULL COMMENT '社員番号',
  `birthday` varchar(16) DEFAULT NULL COMMENT '誕生日',
  `group_id` int(11) DEFAULT NULL COMMENT 'グループID',
  `admin_f` tinyint(2) DEFAULT '1' COMMENT '管理者フラグ',
  `user_vaild_f` tinyint(2) DEFAULT '1' COMMENT 'ユーザー有効フラグ',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ユーザーテーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tb`
--

LOCK TABLES `user_tb` WRITE;
/*!40000 ALTER TABLE `user_tb` DISABLE KEYS */;
INSERT INTO `user_tb` VALUES (1,'test1@test.x.xx','test1','テスト　太郎','0','2020-05-10T00:00',0,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(2,'test2@test.x.xx','test','テストフラグ差分1','100000000','2020-05-11T00:00',1,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(3,'test3@test.x.xx','test','テストフラグ差分2','210000000','2020-06-12T00:00',2,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(4,'test4@test.x.xx','test','テストフラグ差分3','321000000','2020-07-12T00:00',2,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(5,'test5@test.x.xx','test','テストフラグ差分4','432100000','2020-08-12T00:00',1,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(6,'test6@test.x.xx','test','テストフラグ差分5','543210000','2020-09-12T00:00',1,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(7,'test7@test.x.xx','test','テストフラグ差分6','654321000','2020-10-12T00:00',2,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(8,'test8@test.x.xx','test','テストフラグ差分7','765432100','2020-11-12T00:00',2,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(9,'test9@test.x.xx','test','テストフラグ差分8','876543210','2020-12-12T00:00',1,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(10,'test10@test.x.xx','test','テストフラグ差分9','987654321','2020-01-12T00:00',1,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(11,'test11@test.x.xx','test','テストフラグ差分10','0','2020-02-12T00:00',2,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(12,'test12@test.x.xx','test','テストフラグ差分11','0','2020-03-12T00:00',2,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(13,'test13@test.x.xx','test','テストフラグ差分12','0','2020-04-12T00:00',1,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(14,'test14@test.x.xx','test','テストフラグ差分13','0','2020-05-12T00:00',1,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(15,'test15@test.x.xx','test','テストフラグ差分14','0','2020-06-12T00:00',2,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(16,'test16@test.x.xx','test','テストフラグ差分15','0','2020-07-12T00:00',2,0,1,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(17,'test17@test.x.xx','test','テスト無効フラグ','0','2020-08-12T00:00',2,0,0,'2018-11-17 13:44:00','2018-11-17 13:44:00'),(18,'-ー_＿@．. ＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵabcdefghijklmnopqrst','-ー_＿@．. ＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵabcdefghijklmnopqrst','テスト　花子','-ー_＿@．. ＠ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵabcdefghijklmnopqrst','2020-09-12T00:00',1,1,1,'2018-11-17 13:44:00','2018-11-17 13:44:00');
/*!40000 ALTER TABLE `user_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-25  1:55:03
