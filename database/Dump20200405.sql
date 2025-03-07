CREATE DATABASE  IF NOT EXISTS `mypetstore` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mypetstore`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `ACCOUNT`;
CREATE TABLE `ACCOUNT` (
    `USERID` VARCHAR(80) NOT NULL,
    `EMAIL` VARCHAR(80) NOT NULL,
    `FIRSTNAME` VARCHAR(80) NOT NULL,
    `LASTNAME` VARCHAR(80) NOT NULL,
    `STATUS` VARCHAR(2) DEFAULT NULL,
    `ADDR1` VARCHAR(80) NOT NULL,
    `ADDR2` VARCHAR(40) DEFAULT NULL,
    `CITY` VARCHAR(80) NOT NULL,
    `STATE` VARCHAR(80) NOT NULL,
    `ZIP` VARCHAR(20) NOT NULL,
    `COUNTRY` VARCHAR(20) NOT NULL,
    `PHONE` VARCHAR(80) NOT NULL,
    PRIMARY KEY (`USERID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `ACCOUNT` WRITE;
/*!40000 ALTER TABLE `ACCOUNT` DISABLE KEYS */;
INSERT INTO `ACCOUNT` VALUES ('a','a','a','a',NULL,'a','a','aa','a','a','a','a'),('ACID','acid@yourdomain.com','ABC','XYX','OK','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','555-555-5555'),('j2ee','yourname@yourdomain.com','ABC','XYX',NULL,'901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','555-555-5555');
/*!40000 ALTER TABLE `ACCOUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bannerdata`
--

DROP TABLE IF EXISTS `BANNERDATA`;
CREATE TABLE `BANNERDATA` (
    `FAVCATEGORY` VARCHAR(80) NOT NULL,
    `BANNERNAME` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`FAVCATEGORY`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bannerdata`
--

LOCK TABLES `BANNERDATA` WRITE;
/*!40000 ALTER TABLE `BANNERDATA` DISABLE KEYS */;
INSERT INTO `BANNERDATA` VALUES ('BIRDS','<image src="../images/banner_birds.gif">'),('CATS','<image src="../images/banner_cats.gif">'),('DOGS','<image src="../images/banner_dogs.gif">'),('FISH','<image src="../images/banner_fish.gif">'),('REPTILES','<image src="../images/banner_reptiles.gif">');
/*!40000 ALTER TABLE `BANNERDATA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `BOOK`;
CREATE TABLE `BOOK` (
    `ID` INT(11) NOT NULL AUTO_INCREMENT,
    `BOOKNAME` VARCHAR(30) DEFAULT NULL,
    `AUTHOR` VARCHAR(30) DEFAULT NULL,
    `ISBN` VARCHAR(30) DEFAULT NULL,
    `PUB` VARCHAR(30) DEFAULT NULL,
    `INDATE` DATE DEFAULT NULL,
    `TOTAL` INT(11) DEFAULT NULL,
    `LEFT` INT(11) DEFAULT NULL,
    PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `BOOK` WRITE;
/*!40000 ALTER TABLE `BOOK` DISABLE KEYS */;
/*!40000 ALTER TABLE `BOOK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowrecord`
--

DROP TABLE IF EXISTS `BORROWRECORD`;
CREATE TABLE `BORROWRECORD` (
    `ID` INT(11) NOT NULL AUTO_INCREMENT,
    `BOOKID` INT(11) DEFAULT NULL,
    `USERID` INT(11) DEFAULT NULL,
    `BORROWDATE` DATE DEFAULT NULL,
    `SHOULDRETURNDATE` DATE DEFAULT NULL,
    `RETURNDATE` DATE DEFAULT NULL,
    `CONTINUE` INT(11) DEFAULT NULL,
    PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=UTF8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowrecord`
--

LOCK TABLES `BORROWRECORD` WRITE;
/*!40000 ALTER TABLE `BORROWRECORD` DISABLE KEYS */;
/*!40000 ALTER TABLE `BORROWRECORD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `CATEGORY`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `CATEGORY` (
    `CATID` VARCHAR(10) NOT NULL,
    `NAME` VARCHAR(80) DEFAULT NULL,
    `DESCN` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`CATID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `CATEGORY`
--

LOCK TABLES `CATEGORY` WRITE;
/*!40000 ALTER TABLE `CATEGORY` DISABLE KEYS */;
INSERT INTO `CATEGORY` VALUES ('BIRDS','Birds','<image src="../images/birds_icon.gif"><font size="5" color="blue"> Birds</font>'),('CATS','Cats','<image src="../images/cats_icon.gif"><font size="5" color="blue"> Cats</font>'),('DOGS','Dogs','<image src="../images/dogs_icon.gif"><font size="5" color="blue"> Dogs</font>'),('FISH','Fish','<image src="../images/fish_icon.gif"><font size="5" color="blue"> Fish</font>'),('REPTILES','Reptiles','<image src="../images/reptiles_icon.gif"><font size="5" color="blue"> Reptiles</font>');
/*!40000 ALTER TABLE `CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INVENTORY`
--

DROP TABLE IF EXISTS `INVENTORY`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `INVENTORY` (
    `ITEMID` VARCHAR(10) NOT NULL,
    `QTY` INT(11) NOT NULL,
    PRIMARY KEY (`ITEMID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `INVENTORY`
--

LOCK TABLES `INVENTORY` WRITE;
/*!40000 ALTER TABLE `INVENTORY` DISABLE KEYS */;
INSERT INTO `INVENTORY` VALUES ('EST-1',10000),('EST-10',10000),('EST-11',10000),('EST-12',10000),('EST-13',10000),('EST-14',10000),('EST-15',10000),('EST-16',10000),('EST-17',10000),('EST-18',10000),('EST-19',10000),('EST-2',10000),('EST-20',10000),('EST-21',10000),('EST-22',10000),('EST-23',10000),('EST-24',10000),('EST-25',10000),('EST-26',10000),('EST-27',10000),('EST-28',10000),('EST-3',10000),('EST-4',10000),('EST-5',10000),('EST-6',10000),('EST-7',10000),('EST-8',10000),('EST-9',10000);
/*!40000 ALTER TABLE `INVENTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEM`
--

DROP TABLE IF EXISTS `ITEM`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `ITEM` (
    `ITEMID` VARCHAR(10) NOT NULL,
    `PRODUCTID` VARCHAR(10) NOT NULL,
    `LISTPRICE` DECIMAL(10, 2) DEFAULT NULL,
    `UNITCOST` DECIMAL(10, 2) DEFAULT NULL,
    `SUPPLIER` INT(11) DEFAULT NULL,
    `STATUS` VARCHAR(2) DEFAULT NULL,
    `ATTR1` VARCHAR(80) DEFAULT NULL,
    `ATTR2` VARCHAR(80) DEFAULT NULL,
    `ATTR3` VARCHAR(80) DEFAULT NULL,
    `ATTR4` VARCHAR(80) DEFAULT NULL,
    `ATTR5` VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY (`ITEMID`),
    KEY `FK_ITEM_2` (`SUPPLIER`),
    KEY `ITEMPROD` (`PRODUCTID`),
    CONSTRAINT `FK_ITEM_1` FOREIGN KEY (`PRODUCTID`)
        REFERENCES `PRODUCT` (`PRODUCTID`),
    CONSTRAINT `FK_ITEM_2` FOREIGN KEY (`SUPPLIER`)
        REFERENCES `SUPPLIER` (`SUPPID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `ITEM`
--

LOCK TABLES `ITEM` WRITE;
/*!40000 ALTER TABLE `ITEM` DISABLE KEYS */;
INSERT INTO `ITEM` VALUES ('EST-1','FI-SW-01',16.50,10.00,1,'P','Large',NULL,NULL,NULL,NULL),('EST-10','K9-DL-01',18.50,12.00,1,'P','Spotted Adult Female',NULL,NULL,NULL,NULL),('EST-11','RP-SN-01',18.50,12.00,1,'P','Venomless',NULL,NULL,NULL,NULL),('EST-12','RP-SN-01',18.50,12.00,1,'P','Rattleless',NULL,NULL,NULL,NULL),('EST-13','RP-LI-02',18.50,12.00,1,'P','Green Adult',NULL,NULL,NULL,NULL),('EST-14','FL-DSH-01',58.50,12.00,1,'P','Tailless',NULL,NULL,NULL,NULL),('EST-15','FL-DSH-01',23.50,12.00,1,'P','With tail',NULL,NULL,NULL,NULL),('EST-16','FL-DLH-02',93.50,12.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-17','FL-DLH-02',93.50,12.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-18','AV-CB-01',193.50,92.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-19','AV-SB-02',15.50,2.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-2','FI-SW-01',16.50,10.00,1,'P','Small',NULL,NULL,NULL,NULL),('EST-20','FI-FW-02',5.50,2.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-21','FI-FW-02',5.29,1.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-22','K9-RT-02',135.50,100.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-23','K9-RT-02',145.49,100.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-24','K9-RT-02',255.50,92.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-25','K9-RT-02',325.29,90.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-26','K9-CW-01',125.50,92.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-27','K9-CW-01',155.29,90.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-28','K9-RT-01',155.29,90.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-3','FI-SW-02',18.50,12.00,1,'P','Toothless',NULL,NULL,NULL,NULL),('EST-4','FI-FW-01',18.50,12.00,1,'P','Spotted',NULL,NULL,NULL,NULL),('EST-5','FI-FW-01',18.50,12.00,1,'P','Spotless',NULL,NULL,NULL,NULL),('EST-6','K9-BD-01',18.50,12.00,1,'P','Male Adult',NULL,NULL,NULL,NULL),('EST-7','K9-BD-01',18.50,12.00,1,'P','Female Puppy',NULL,NULL,NULL,NULL),('EST-8','K9-PO-02',18.50,12.00,1,'P','Male Puppy',NULL,NULL,NULL,NULL),('EST-9','K9-DL-01',18.50,12.00,1,'P','Spotless Male Puppy',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LINEITEM`
--

DROP TABLE IF EXISTS `LINEITEM`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `LINEITEM` (
    `ORDERID` INT(11) NOT NULL,
    `LINENUM` INT(11) NOT NULL,
    `ITEMID` VARCHAR(10) NOT NULL,
    `QUANTITY` INT(11) NOT NULL,
    `UNITPRICE` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`ORDERID`, `LINENUM`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `LINEITEM`
--

LOCK TABLES `LINEITEM` WRITE;
/*!40000 ALTER TABLE `LINEITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `LINEITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `ORDERS` (
    `ORDERID` INT(11) NOT NULL,
    `USERID` VARCHAR(80) NOT NULL,
    `ORDERDATE` DATE NOT NULL,
    `SHIPADDR1` VARCHAR(80) NOT NULL,
    `SHIPADDR2` VARCHAR(80) DEFAULT NULL,
    `SHIPCITY` VARCHAR(80) NOT NULL,
    `SHIPSTATE` VARCHAR(80) NOT NULL,
    `SHIPZIP` VARCHAR(20) NOT NULL,
    `SHIPCOUNTRY` VARCHAR(20) NOT NULL,
    `BILLADDR1` VARCHAR(80) NOT NULL,
    `BILLADDR2` VARCHAR(80) DEFAULT NULL,
    `BILLCITY` VARCHAR(80) NOT NULL,
    `BILLSTATE` VARCHAR(80) NOT NULL,
    `BILLZIP` VARCHAR(20) NOT NULL,
    `BILLCOUNTRY` VARCHAR(20) NOT NULL,
    `COURIER` VARCHAR(80) NOT NULL,
    `TOTALPRICE` DECIMAL(10 , 2 ) NOT NULL,
    `BILLTOFIRSTNAME` VARCHAR(80) NOT NULL,
    `BILLTOLASTNAME` VARCHAR(80) NOT NULL,
    `SHIPTOFIRSTNAME` VARCHAR(80) NOT NULL,
    `SHIPTOLASTNAME` VARCHAR(80) NOT NULL,
    `CREDITCARD` VARCHAR(80) NOT NULL,
    `EXPRDATE` VARCHAR(7) NOT NULL,
    `CARDTYPE` VARCHAR(80) NOT NULL,
    `LOCALE` VARCHAR(80) NOT NULL,
    PRIMARY KEY (`ORDERID`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERSTATUS`
--

DROP TABLE IF EXISTS `ORDERSTATUS`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `ORDERSTATUS` (
    `ORDERID` INT(11) NOT NULL,
    `LINENUM` INT(11) NOT NULL,
    `TIMESTAMP` DATE NOT NULL,
    `STATUS` VARCHAR(2) NOT NULL,
    PRIMARY KEY (`ORDERID` , `LINENUM`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `ORDERSTATUS`
--
LOCK TABLES `ORDERSTATUS` WRITE;
/*!40000 ALTER TABLE `ORDERSTATUS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDERSTATUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `PRODUCT` (
    `PRODUCTID` VARCHAR(10) NOT NULL,
    `CATEGORY` VARCHAR(10) NOT NULL,
    `NAME` VARCHAR(80) DEFAULT NULL,
    `DESCN` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`PRODUCTID`),
    KEY `PRODUCTCAT` (`CATEGORY`),
    KEY `PRODUCTNAME` (`NAME`),
    CONSTRAINT `FK_PRODUCT_1` FOREIGN KEY (`CATEGORY`)
        REFERENCES `CATEGORY` (`CATID`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `PRODUCT`
--

LOCK TABLES `PRODUCT` WRITE;
/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;
INSERT INTO `PRODUCT` VALUES ('AV-CB-01','BIRDS','Amazon Parrot','<image src=\"../images/bird2.gif\">Great companion for up to 75 years'),('AV-SB-02','BIRDS','Finch','<image src=\"../images/bird1.gif\">Great stress reliever'),('FI-FW-01','FISH','Koi','<image src=\"../images/fish3.gif\">Fresh Water fish from Japan'),('FI-FW-02','FISH','Goldfish','<image src=\"../images/fish2.gif\">Fresh Water fish from China'),('FI-SW-01','FISH','Angelfish','<image src=\"../images/fish1.jpg\">Salt Water fish from Australia'),('FI-SW-02','FISH','Tiger Shark','<image src=\"../images/fish4.gif\">Salt Water fish from Australia'),('FL-DLH-02','CATS','Persian','<image src=\"../images/cat1.gif\">Friendly house cat, doubles as a princess'),('FL-DSH-01','CATS','Manx','<image src=\"../images/cat3.gif\">Great for reducing mouse populations'),('K9-BD-01','DOGS','Bulldog','<image src=\"../images/dog2.gif\">Friendly dog from England'),('K9-CW-01','DOGS','Chihuahua','<image src=\"../images/dog4.gif\">Great companion dog'),('K9-DL-01','DOGS','Dalmation','<image src=\"../images/dog5.gif\">Great dog for a Fire Station'),('K9-PO-02','DOGS','Poodle','<image src=\"../images/dog6.gif\">Cute dog from France'),('K9-RT-01','DOGS','Golden Retriever','<image src=\"../images/dog1.gif\">Great family dog'),('K9-RT-02','DOGS','Labrador Retriever','<image src=\"../images/dog5.gif\">Great hunting dog'),('RP-LI-02','REPTILES','Iguana','<image src=\"../images/lizard2.gif\">Friendly green friend'),('RP-SN-01','REPTILES','Rattlesnake','<image src=\"../images/lizard3.gif\">Doubles as a watch dog');
/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROFILE`
--

DROP TABLE IF EXISTS `PROFILE`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `PROFILE` (
    `USERID` VARCHAR(80) NOT NULL,
    `LANGPREF` VARCHAR(80) NOT NULL,
    `FAVCATEGORY` VARCHAR(30) DEFAULT NULL,
    `MYLISTOPT` TINYINT(1) DEFAULT NULL,
    `BANNEROPT` TINYINT(1) DEFAULT NULL,
    PRIMARY KEY (`USERID`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `PROFILE`
--

LOCK TABLES `PROFILE` WRITE;
/*!40000 ALTER TABLE `PROFILE` DISABLE KEYS */;
INSERT INTO `PROFILE` VALUES ('a','japanese','DOGS',NULL,NULL),('ACID','english','CATS',1,1),('j2ee','english','FISH',1,1);
/*!40000 ALTER TABLE `PROFILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `SEQUENCE` (
    `NAME` VARCHAR(30) NOT NULL,
    `NEXTID` INT(11) NOT NULL,
    PRIMARY KEY (`NAME`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('linenum',1000),('ordernum',1000);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SIGNON`
--

DROP TABLE IF EXISTS `SIGNON`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `SIGNON` (
    `USERNAME` VARCHAR(25) NOT NULL,
    `PASSWORD` VARCHAR(25) NOT NULL,
    PRIMARY KEY (`USERNAME`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `SIGNON`
--

LOCK TABLES `SIGNON` WRITE;
/*!40000 ALTER TABLE `SIGNON` DISABLE KEYS */;
INSERT INTO `SIGNON` VALUES ('a','a'),('ACID','ACID'),('j2ee','j2ee');
/*!40000 ALTER TABLE `SIGNON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUPPLIER`
--

DROP TABLE IF EXISTS `SUPPLIER`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `SUPPLIER` (
    `SUPPID` INT(11) NOT NULL,
    `NAME` VARCHAR(80) DEFAULT NULL,
    `STATUS` VARCHAR(2) NOT NULL,
    `ADDR1` VARCHAR(80) DEFAULT NULL,
    `ADDR2` VARCHAR(80) DEFAULT NULL,
    `CITY` VARCHAR(80) DEFAULT NULL,
    `STATE` VARCHAR(80) DEFAULT NULL,
    `ZIP` VARCHAR(5) DEFAULT NULL,
    `PHONE` VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY (`SUPPID`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `SUPPLIER`
--

LOCK TABLES `SUPPLIER` WRITE;
/*!40000 ALTER TABLE `SUPPLIER` DISABLE KEYS */;
INSERT INTO `SUPPLIER` VALUES (1,'XYZ Pets','AC','600 Avon Way','','Los Angeles','CA','94024','212-947-0797'),(2,'ABC Pets','AC','700 Abalone Way','','San Francisco ','CA','94024','415-947-0797');
/*!40000 ALTER TABLE `SUPPLIER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @SAVED_CS_CLIENT     = @@CHARACTER_SET_CLIENT */;
/*!50503 SET CHARACTER_SET_CLIENT = utf8mb4 */;
CREATE TABLE `USER` (
    `ID` INT(11) NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(20) DEFAULT NULL,
    `PASSWORD` VARCHAR(20) DEFAULT NULL,
    `USERTYPE` INT(11) DEFAULT NULL,
    PRIMARY KEY (`ID`)
)  ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=UTF8;
/*!40101 SET CHARACTER_SET_CLIENT = @SAVED_CS_CLIENT */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-05 13:50:24
