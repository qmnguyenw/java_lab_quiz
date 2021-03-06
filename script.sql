USE [master]
GO

CREATE DATABASE [OnlineQuiz]
GO
ALTER DATABASE [OnlineQuiz] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [OnlineQuiz].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [OnlineQuiz] SET ARITHABORT OFF 
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [OnlineQuiz] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [OnlineQuiz] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [OnlineQuiz] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [OnlineQuiz] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [OnlineQuiz] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [OnlineQuiz] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [OnlineQuiz] SET  DISABLE_BROKER 
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [OnlineQuiz] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [OnlineQuiz] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [OnlineQuiz] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [OnlineQuiz] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [OnlineQuiz] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [OnlineQuiz] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [OnlineQuiz] SET RECOVERY FULL 
GO
ALTER DATABASE [OnlineQuiz] SET  MULTI_USER 
GO
ALTER DATABASE [OnlineQuiz] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [OnlineQuiz] SET DB_CHAINING OFF 
GO
ALTER DATABASE [OnlineQuiz] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [OnlineQuiz] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [OnlineQuiz] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'OnlineQuiz', N'ON'
GO
USE [OnlineQuiz]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
	[type_account] [int] NULL,
	[email] [nvarchar](50) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[quiz_id] [nvarchar](50) NULL,
	[question] [text] NULL,
	[answer] [text] NULL,
	[answer_correct] [nvarchar](50) NULL,
 CONSTRAINT [PK_Quiz] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz](
	[id] [nvarchar](50) NOT NULL,
	[name] [nvarchar](200) NULL,
	[creator] [nvarchar](50) NULL,
	[createdDate] [date] NULL CONSTRAINT [DF_Quiz_createdDate]  DEFAULT (getdate()),
 CONSTRAINT [PK_Quiz_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Type_Account](
	[id] [int] NOT NULL,
	[type] [nvarchar](50) NULL,
 CONSTRAINT [PK_Type_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Account] ([username], [password], [type_account], [email]) VALUES (N'user1', N'202CB962AC59075B964B07152D234B70', 2, NULL)
INSERT [dbo].[Account] ([username], [password], [type_account], [email]) VALUES (N'user2', N'202CB962AC59075B964B07152D234B70', 1, N'aaaa@gmail.com')
INSERT [dbo].[Account] ([username], [password], [type_account], [email]) VALUES (N'user3', N'202CB962AC59075B964B07152D234B70', 1, N'zzzz@gmail.com')
SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (1, N'test1', N'The name of Capital of Vietnam after year of 1975?', N'Ha Noi|Hai Phong|Tp Ho Chi Minh|Ca Mau', N'Ha Noi')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (2, N'test1', N'1+1=?', N'1|2|3|4', N'2')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (3, N'test1', N'2+2=?', N'1|2|3|4', N'4')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (4, N'test1', N'3+3=? ', N'3|4|5|6', N'6')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (5, N'test2', N'4+4=?', N'4|8|6|10', N'8')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (6, N'test3', N'3+4=?', N'1|2|5|7', N'7')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (9, N'test5', N'20+20=?', N'12|40|25|62', N'40')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (10, N'test5', N'40+60=?', N'25|46|17|100', N'100')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (11, N'test5', N'5+10=?', N'15|24|64|20', N'15')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (12, N'test5', N'10+25=?', N'35|24|23|14', N'35')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (13, N'test4', N'3>9', N'Dung|Sai', N'Sai')
INSERT [dbo].[Question] ([id], [quiz_id], [question], [answer], [answer_correct]) VALUES (14, N'test4', N'5=6?', N'sai|dung', N'sai')
SET IDENTITY_INSERT [dbo].[Question] OFF
INSERT [dbo].[Quiz] ([id], [name], [creator], [createdDate]) VALUES (N'test1', N'Test 1', N'user1', CAST(N'2021-06-07' AS Date))
INSERT [dbo].[Quiz] ([id], [name], [creator], [createdDate]) VALUES (N'test2', N'Test 2', N'user1', CAST(N'2021-06-07' AS Date))
INSERT [dbo].[Quiz] ([id], [name], [creator], [createdDate]) VALUES (N'test3', N'Test 3', N'user1', CAST(N'2021-06-07' AS Date))
INSERT [dbo].[Quiz] ([id], [name], [creator], [createdDate]) VALUES (N'test4', N'Test 4', N'user1', CAST(N'2021-06-07' AS Date))
INSERT [dbo].[Quiz] ([id], [name], [creator], [createdDate]) VALUES (N'test5', N'Test 5', N'user1', CAST(N'2021-06-07' AS Date))
INSERT [dbo].[Type_Account] ([id], [type]) VALUES (1, N'Student')
INSERT [dbo].[Type_Account] ([id], [type]) VALUES (2, N'Teacher')
ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([type_account])
REFERENCES [dbo].[Type_Account] ([id])
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK__Question__quiz_i__3A81B327] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[Quiz] ([id])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK__Question__quiz_i__3A81B327]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK__Quiz__creator__3B75D760] FOREIGN KEY([creator])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK__Quiz__creator__3B75D760]
GO
USE [master]
GO
ALTER DATABASE [OnlineQuiz] SET  READ_WRITE 
GO
