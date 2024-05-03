terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region     = var.region
  access_key = var.access_key
  secret_key = var.secret_key
}

resource "aws_db_instance" "rds" {
  identifier            = "rds-instance"
  allocated_storage     = 20
  storage_type          = "gp2"
  engine                = "postgres"
  engine_version        = "12.6"
  instance_class        = "db.t2.micro"
  db_name               = "laudai"
  username              = "admin"
  password              = "2engenhariadesoftware!"
  parameter_group_name  = "default.postgres12"
  publicly_accessible  = false

}

resource "aws_elastic_beanstalk_application" "eb" {
  name        = "laudai-eb"
  description = "instancias da aplicacao"
}

resource "aws_elastic_beanstalk_environment" "eb_env" {
  name                = "laudai-env"
  application         = aws_elastic_beanstalk_application.eb.name
  solution_stack_name = "64bit Amazon Linux 2023 v4.0.11 running Python 3.11"

  setting {
    namespace = "aws:ec2:vpc"
    name      = "VPCId"
    value     = var.vpc_id
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "Subnets"
    value     = var.subnets
  }
  setting {
    namespace = "aws:ec2:vpc"
    name      = "AssociatePublicIpAddress"
    value     =  "False"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = "t2.micro"
  }


}

