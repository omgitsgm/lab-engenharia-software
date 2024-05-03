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
  engine_version        = "12"
  instance_class        = "db.t2.micro"
  db_name               = "laudai"
  username              = var.db_user
  password              = var.db_password
  parameter_group_name  = "default.postgres16"
  publicly_accessible  = false

}

