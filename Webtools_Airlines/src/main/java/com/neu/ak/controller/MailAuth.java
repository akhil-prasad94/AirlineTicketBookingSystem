package com.neu.ak.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class MailAuth extends Authenticator
{
public PasswordAuthentication getPasswordAuthentication()
{
    String username = "healthfirst5100@gmail.com";
    String password = "mskvsvaed";
    return new PasswordAuthentication(username, password);
}
}