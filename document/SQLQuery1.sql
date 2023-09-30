CREATE TABLE [Staff] (
  [staffID] varchar(20),
  [adminID] varchar(20),
  [account] varchar(20),
  [password] varchar(20),
  [firstName] nvarchar,
  [lastName] nvarchar,
  [role] varchar(20),
  [birth] date,
  PRIMARY KEY ([staffID])
);

CREATE TABLE [Pet] (
  [petID] varchar(20),
  [userID] varchar(20),
  [namepet] nvarchar,
  [type] nvarchar,
  [description] nvarchar,
  [datepublic] date,
  [phone] varchar(20),
  PRIMARY KEY ([petID])
);

CREATE TABLE [UserManagement] (
  [staffID] varchar(20),
  [userID] varchar(20),
);

CREATE TABLE [Product] (
  [productID] varchar(20),
  [userID] varchar(20),
  [productname] nvarchar,
  [price] int,
  [image] varchar(20),
  [description] nvarchar,
  [date] date,
  [phone] varchar(20),
  [type] varchar(20),
  PRIMARY KEY ([productID])
);

CREATE TABLE [PostDetail] (
  [postID] varchar(20),
  [userID] varchar(20),
  [title] nvarchar,
  [content] nvarchar,
  [publishdate] date,
  [image] varchar(20),
  [comment] nvarchar,
  [like] int,
  PRIMARY KEY ([postID])
);

CREATE TABLE [Lover] (
  [userID] varchar(20),
  [password] varchar(20),
  [email] varchar(20),
  [firstName] nvarchar,
  [lastName] nvarchar,
  [phone] varchar(20),
  [address] nvarchar,
  [birth] date,
  [sex] varchar(20),
  [role] varchar(20),
  PRIMARY KEY ([userID])
);

CREATE TABLE [Admin] (
  [adminID] varchar(20),
  [account] varchar(20),
  [password] varchar(20),
  [lastname] nvarchar,
  [firstname] nvarchar,
  [role] varchar(20),
  PRIMARY KEY ([adminID]),
);

CREATE TABLE [PostMangement] (
  [staffID] varchar(20),
  [userID] varchar(20),
  [postID] varchar(20),
);

CREATE TABLE [PostComment] (
  [commentID] varchar(20),
  [postID] varchar(20),
  [userID] varchar(20),
  [comment] nvarchar,
  [publishDateComment] date,
  PRIMARY KEY ([commentID]),
  
);

CREATE TABLE [PostLike] (
  [likeID] varchar(20),
  [postID] varchar(20),
  [userID] varchar(20),
  [like] bit,
  PRIMARY KEY ([likeID]),
);
