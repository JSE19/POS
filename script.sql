USE [master]
GO
/****** Object:  Database [AppMartInvoice]    Script Date: 9/12/2018 3:12:45 PM ******/
CREATE DATABASE [AppMartInvoice]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AppMartInvoice', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\AppMartInvoice.mdf' , SIZE = 307200KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'AppMartInvoice_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\AppMartInvoice_log.ldf' , SIZE = 102400KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [AppMartInvoice] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AppMartInvoice].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AppMartInvoice] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AppMartInvoice] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AppMartInvoice] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AppMartInvoice] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AppMartInvoice] SET ARITHABORT OFF 
GO
ALTER DATABASE [AppMartInvoice] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AppMartInvoice] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AppMartInvoice] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AppMartInvoice] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AppMartInvoice] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AppMartInvoice] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AppMartInvoice] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AppMartInvoice] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AppMartInvoice] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AppMartInvoice] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AppMartInvoice] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AppMartInvoice] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AppMartInvoice] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AppMartInvoice] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AppMartInvoice] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AppMartInvoice] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AppMartInvoice] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AppMartInvoice] SET RECOVERY FULL 
GO
ALTER DATABASE [AppMartInvoice] SET  MULTI_USER 
GO
ALTER DATABASE [AppMartInvoice] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AppMartInvoice] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AppMartInvoice] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AppMartInvoice] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [AppMartInvoice] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'AppMartInvoice', N'ON'
GO
USE [AppMartInvoice]
GO
/****** Object:  Table [dbo].[CustomerReg]    Script Date: 9/12/2018 3:12:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CustomerReg](
	[CustomerId] [int] IDENTITY(1,1) NOT NULL,
	[CusName] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_CustomerReg] PRIMARY KEY CLUSTERED 
(
	[CustomerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Items]    Script Date: 9/12/2018 3:12:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Items](
	[ItemId] [int] IDENTITY(1,1) NOT NULL,
	[ItemName] [nvarchar](50) NOT NULL,
	[ItemDescription] [nvarchar](50) NULL,
	[UnitPrice] [float] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KeepTransaction]    Script Date: 9/12/2018 3:12:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KeepTransaction](
	[TransactionId] [int] IDENTITY(1,1) NOT NULL,
	[TransactionTime] [time](7) NOT NULL,
	[TransactionDate] [nvarchar](50) NOT NULL,
	[Item] [nvarchar](50) NOT NULL,
	[ItemQuantity] [int] NOT NULL,
	[Total] [float] NOT NULL,
	[UserId] [nvarchar](50) NOT NULL,
	[CustomerId] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[StaffPosition]    Script Date: 9/12/2018 3:12:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StaffPosition](
	[PositionId] [int] NOT NULL,
	[PositionName] [nvarchar](50) NOT NULL,
	[PositionDescription] [nvarchar](100) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[StaffReg]    Script Date: 9/12/2018 3:12:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StaffReg](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[FName] [nvarchar](50) NOT NULL,
	[LName] [nvarchar](50) NOT NULL,
	[Gender] [nvarchar](10) NOT NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[PassWord] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[KeepTransaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_CustomerReg] FOREIGN KEY([CustomerId])
REFERENCES [dbo].[CustomerReg] ([CustomerId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[KeepTransaction] CHECK CONSTRAINT [FK_Transaction_CustomerReg]
GO
USE [master]
GO
ALTER DATABASE [AppMartInvoice] SET  READ_WRITE 
GO
