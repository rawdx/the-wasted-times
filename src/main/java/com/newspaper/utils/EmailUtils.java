package com.newspaper.utils;

/**
 * Utility class for generating HTML content for email templates.
 */
public class EmailUtils {

    /**
     * Generates HTML content for a verification email with the provided verification link.
     *
     * @param link The verification link to be included in the email.
     * @return HTML content for the verification email.
     */
    public static String generateVerificationEmailHtml(String link) {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\""+
                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">"+
                "<head>"+
                "    <!--[if gte mso 9]>"+
                "    <xml>"+
                "        <o:OfficeDocumentSettings>"+
                "            <o:AllowPNG/>"+
                "            <o:PixelsPerInch>96</o:PixelsPerInch>"+
                "        </o:OfficeDocumentSettings>"+
                "    </xml>"+
                "    <![endif]-->"+
                "    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\">"+
                "    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">"+
                "    <meta name=\"x-apple-disable-message-reformatting\">"+
                "    <!--[if !mso]><!-->"+
                "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"><!--<![endif]-->"+
                "    <title></title>"+
                "    <style type=\"text/css\">"+
                "        @import url('https://fonts.googleapis.com/css2?family=Protest+Revolution&display=swap');"+
                "        @media only screen and (min-width: 620px) {"+
                "            .u-row {"+
                "                width: 600px !important;"+
                "            }"+
                "            .u-row .u-col {"+
                "                vertical-align: top;"+
                "            }"+
                "            .u-row .u-col-100 {"+
                "                width: 600px !important;"+
                "            }"+
                "        }"+
                "        @media (max-width: 620px) {"+
                "            .u-row-container {"+
                "                max-width: 100% !important;"+
                "                padding-left: 0px !important;"+
                "                padding-right: 0px !important;"+
                "            }"+
                "            .u-row .u-col {"+
                "                min-width: 320px !important;"+
                "                max-width: 100% !important;"+
                "                display: block !important;"+
                "            }"+
                "            .u-row {"+
                "                width: 100% !important;"+
                "            }"+
                "            .u-col {"+
                "                width: 100% !important;"+
                "            }"+
                "            .u-col > div {"+
                "                margin: 0 auto;"+
                "            }"+
                "        }"+
                "        body {"+
                "            margin: 0;"+
                "            padding: 0;"+
                "        }"+
                "        table,"+
                "        tr,"+
                "        td {"+
                "            vertical-align: top;"+
                "            border-collapse: collapse;"+
                "        }"+
                "        p {"+
                "            margin: 0;"+
                "        }"+
                "        .ie-container table,"+
                "        .mso-container table {"+
                "            table-layout: fixed;"+
                "        }"+
                "        * {"+
                "            line-height: inherit;"+
                "        }"+
                "        a[x-apple-data-detectors='true'] {"+
                "            color: inherit !important;"+
                "            text-decoration: none !important;"+
                "        }"+
                "        table, td {"+
                "            color: #000000;"+
                "        }"+
                "        #u_body a {"+
                "            color: #0000ee;"+
                "            text-decoration: underline;"+
                "        }"+
                "        @media (max-width: 480px) {"+
                "            #u_content_heading_4 .v-font-size {"+
                "                font-size: 22px !important;"+
                "            }"+
                "            #u_content_heading_4 .v-line-height {"+
                "                line-height: 170% !important;"+
                "            }"+
                "            #u_content_menu_1 .v-font-size {"+
                "                font-size: 11px !important;"+
                "            }"+
                "            #u_content_menu_1 .v-padding {"+
                "                padding: 0px 15px 5px !important;"+
                "            }"+
                "            #u_content_image_2 .v-container-padding-padding {"+
                "                padding: 30px 10px 10px !important;"+
                "            }"+
                "            #u_content_image_2 .v-src-width {"+
                "                width: auto !important;"+
                "            }"+
                "            #u_content_image_2 .v-src-max-width {"+
                "                max-width: 45% !important;"+
                "            }"+
                "            #u_content_heading_1 .v-container-padding-padding {"+
                "                padding: 9px 10px 0px !important;"+
                "            }"+
                "            #u_content_heading_1 .v-font-size {"+
                "                font-size: 26px !important;"+
                "            }"+
                "            #u_content_heading_1 .v-line-height {"+
                "                line-height: 150% !important;"+
                "            }"+
                "            #u_content_text_5 .v-font-size {"+
                "                font-size: 14px !important;"+
                "            }"+
                "            #u_content_button_1 .v-container-padding-padding {"+
                "                padding: 13px 10px 15px !important;"+
                "            }"+
                "            #u_content_button_1 .v-size-width {"+
                "                width: 65% !important;"+
                "            }"+
                "            #u_content_button_1 .v-padding {"+
                "                padding: 10px 20px !important;"+
                "            }"+
                "            #u_content_text_1 .v-container-padding-padding {"+
                "                padding: 10px 10px 15px !important;"+
                "            }"+
                "            #u_content_text_1 .v-font-size {"+
                "                font-size: 12px !important;"+
                "            }"+
                "            #u_content_heading_3 .v-container-padding-padding {"+
                "                padding: 25px 19px 10px !important;"+
                "            }"+
                "            #u_content_heading_3 .v-font-size {"+
                "                font-size: 21px !important;"+
                "            }"+
                "            #u_content_heading_3 .v-text-align {"+
                "                text-align: center !important;"+
                "            }"+
                "            #u_content_heading_3 .v-line-height {"+
                "                line-height: 130% !important;"+
                "            }"+
                "            #u_content_text_4 .v-container-padding-padding {"+
                "                padding: 0px 20px 15px !important;"+
                "            }"+
                "            #u_content_text_4 .v-text-align {"+
                "                text-align: center !important;"+
                "            }"+
                "        }"+
                "    </style>"+
                "    <!--[if !mso]><!-->"+
                "    <link href=\"https://fonts.googleapis.com/css2?family=Protest+Revolution&display=swap\" rel=\"stylesheet\" type=\"text/css\">"+
                "    <link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">"+
                "    <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">"+
                "    <!--<![endif]-->"+
                "</head>"+
                "<body class=\"clean-body u_body\""+
                "      style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">"+
                "<!--[if IE]>"+
                "<div class=\"ie-container\"><![endif]-->"+
                "<!--[if mso]>"+
                "<div class=\"mso-container\"><![endif]-->"+
                "<table cellpadding=\"0\""+
                "       cellspacing=\"0\""+
                "       id=\"u_body\""+
                "       style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\">"+
                "    <tbody>"+
                "    <tr style=\"vertical-align: top\">"+
                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">"+
                "            <!--[if (mso)|(IE)]>"+
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                <tr>"+
                "                    <td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_heading_4\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <table width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td><![endif]-->"+
                "                                                <h1 class=\"v-text-align v-line-height v-font-size\""+
                "                                                    style=\"margin: 0px; line-height: 150%; text-align: center; word-wrap: break-word; font-family: 'Protest Revolution', sans-serif; font-size: 35px; color: #404040\">"+
                "                                                    <span style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\">The Wasted Times</span></span></span></span></span></span></span></span></span></span></span></span></span>"+
                "                                                </h1>"+
                "                                                <!--[if mso]></td></tr></table><![endif]-->"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"598\""+
                "                            style=\"width: 598px;padding: 0px;border-top: 1px solid #ffffff;border-left: 1px solid #ffffff;border-right: 1px solid #ffffff;border-bottom: 1px solid #ffffff;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 1px solid #ffffff;border-left: 1px solid #ffffff;border-right: 1px solid #ffffff;border-bottom: 1px solid #ffffff;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_menu_1\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"menu\" style=\"text-align:center\">"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\""+
                "                                                           cellspacing=\"0\" align=\"center\">"+
                "                                                        <tr><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=" + link +
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        Home"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=" + link +
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        Page"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=" + link +
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        About US"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=" + link +
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        Contact Us"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]></tr></table><![endif]-->"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 10px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 10px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_image_2\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td align=\"center\""+
                "                                                            class=\"v-text-align\""+
                "                                                            style=\"padding-right: 0px;padding-left: 0px;\">"+
                "                                                            <img align=\"center\" alt=\"image\" border=\"0\""+
                "                                                                 class=\"v-src-width v-src-max-width\" src=\"cid:mail.jpeg\""+
                "Content-Disposition: inline;"+
                "                                                                 style=\" 'display:inline;' outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 42%;max-width: 243.6px;\""+
                "                                                                 title=\"image\" width=\"243.6\"/>"+
                "                                                        </td>"+
                "                                                    </tr>"+
                "                                                </table>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_heading_1\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 0px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <table width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td><![endif]-->"+
                "                                                <h1 class=\"v-text-align v-line-height v-font-size\""+
                "                                                    style=\"margin: 0px; color: #485696; line-height: 160%; text-align: center; word-wrap: break-word; font-size: 35px; font-weight: 400;\">"+
                "                                                    <span><span><span><span><span><span><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiNjVKak1FdmhsNGVRclpybThWU3JnaSIsInBhc3RlSUQiOjE5Mjk0NTkzOTgsImRhdGFUeXBlIjoic2NlbmUifQo=(/figmeta)-->\""+
                "                                                            style=\"line-height: 39px;\"></span><span"+
                "                                                            style=\"line-height: 39px;\"></span><strong>Verify your email</strong></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span>"+
                "                                                </h1>"+
                "                                                <!--[if mso]></td></tr></table><![endif]-->"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_text_5\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"v-text-align v-line-height v-font-size\""+
                "                                                     style=\"font-size: 16px; color: #485696; line-height: 140%; text-align: center; word-wrap: break-word;\">"+
                "                                                    <p style=\"line-height: 140%;\">Thank you for choosing us! To complete"+
                "                                                        your account activation, please click the link below to verify"+
                "                                                        your email address.</p>"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_button_1\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 20px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <style>.v-button {"+
                "                                                    background: transparent !important;"+
                "                                                }</style><![endif]-->"+
                "                                                <div align=\"center\" class=\"v-text-align\">"+
                "                                                    <!--[if mso]>"+
                "                                                    <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\""+
                "                                                                 xmlns:w=\"urn:schemas-microsoft-com:office:word\""+
                "                                                                 href=" + link +
                "                                                                 style=\"height:37px; v-text-anchor:middle; width:132px;\""+
                "                                                                 arcsize=\"11%\" stroke=\"f\" fillcolor=\"#e65815\">"+
                "                                                        <w:anchorlock/>"+
                "                                                        <center style=\"color:#ffffff;\"><![endif]-->"+
                "                                                    <a class=\"v-button v-size-width v-font-size\" href=" + link +
                "                                                       style=\"box-sizing: border-box;display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #e65815; border-radius: 4px;-webkit-border-radius: 4px; -moz-border-radius: 4px; width:auto; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;font-size: 14px;\""+
                "                                                       target=\"_blank\">"+
                "                                                        <span class=\"v-line-height v-padding\""+
                "                                                              style=\"display:block;padding:10px 20px;line-height:120%;\"><strong><span"+
                "                                                                style=\"line-height: 16.8px;\">Click to Verify</span></strong></span>"+
                "                                                    </a>"+
                "                                                    <!--[if mso]></center></v:roundrect><![endif]-->"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                           role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:'Raleway',sans-serif;\">"+
                "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                                       height=\"0px\""+
                "                                                       style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 10px solid #ffffff;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\""+
                "                                                       width=\"100%\">"+
                "                                                    <tbody>"+
                "                                                    <tr style=\"vertical-align: top\">"+
                "                                                        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">"+
                "                                                            <span> </span>"+
                "                                                        </td>"+
                "                                                    </tr>"+
                "                                                    </tbody>"+
                "                                                </table>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_text_1\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 50px 10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"v-text-align v-line-height v-font-size\""+
                "                                                     style=\"font-size: 13px; color: #485696; line-height: 140%; text-align: center; word-wrap: break-word;\">"+
                "                                                    <p style=\"line-height: 140%;\"><span"+
                "                                                            data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiNjVKak1FdmhsNGVRclpybThWU3JnaSIsInBhc3RlSUQiOjQ0MjAzNDA0NywiZGF0YVR5cGUiOiJzY2VuZSJ9Cg==(/figmeta)-->\""+
                "                                                            style=\"line-height: 19.6px;\"></span>If you encounter any"+
                "                                                        issues or did not request this verification, please contact our"+
                "                                                        support team at [Customer Support Email or Phone Number]</p>"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 10px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 10px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                           role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                                       height=\"0px\""+
                "                                                       style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px dashed #485696;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\""+
                "                                                       width=\"100%\">"+
                "                                                    <tbody>"+
                "                                                    <tr style=\"vertical-align: top\">"+
                "                                                        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">"+
                "                                                            <span> </span>"+
                "                                                        </td>"+
                "                                                    </tr>"+
                "                                                    </tbody>"+
                "                                                </table>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_heading_3\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px 35px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <table width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td><![endif]-->"+
                "                                                <h1 class=\"v-text-align v-line-height v-font-size\""+
                "                                                    style=\"margin: 0px; color: #485696; line-height: 140%; text-align: center; word-wrap: break-word; font-size: 22px; font-weight: 400;\">"+
                "      <span><span><span><span><span><span><span><span><span><span><span style=\"line-height: 28.6px;\"><span"+
                "              style=\"line-height: 28.6px;\"><span style=\"line-height: 28.6px;\"><span"+
                "              style=\"line-height: 28.6px;\"><span style=\"line-height: 28.6px;\"><span"+
                "              style=\"line-height: 28.6px;\"><span style=\"line-height: 28.6px;\"><span"+
                "              style=\"line-height: 28.6px;\"><span style=\"line-height: 28.6px;\"><span"+
                "              data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiMlBQSXNjNFd3Q2ZlU0tINFZVMlBXeiIsInBhc3RlSUQiOjk3MDc2NDM4NywiZGF0YVR5cGUiOiJzY2VuZSJ9Cg==(/figmeta)-->\""+
                "              style=\"line-height: 28.6px;\"></span><span"+
                "              style=\"line-height: 28.6px;\"></span><strong>Thanks for the"+
                "                                              support!</strong></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span>"+
                "                                                </h1>"+
                "                                                <!--[if mso]></td></tr></table><![endif]-->"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_text_4\" role=\"presentation\""+
                "                                           style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 60px 15px 35px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"v-text-align v-line-height v-font-size\""+
                "                                                     style=\"font-size: 13px; color: #485696; line-height: 170%; text-align: center; word-wrap: break-word;\">"+
                "                                                    <p style=\"line-height: 170%;\"><span"+
                "                                                            data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiMlBQSXNjNFd3Q2ZlU0tINFZVMlBXeiIsInBhc3RlSUQiOjE5NDM5MjkyMjcsImRhdGFUeXBlIjoic2NlbmUifQo=(/figmeta)-->\""+
                "                                                            style=\"line-height: 22.1px;\"></span>If you have any"+
                "                                                        questions, please visit our [Help Center] or contact our support"+
                "                                                        team at [Customer Support Email or Phone Number]</p>"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->"+
                "        </td>"+
                "    </tr>"+
                "    </tbody>"+
                "</table>"+
                "<!--[if mso]></div><![endif]-->"+
                "<!--[if IE]></div><![endif]-->"+
                "</body>"+
                "</html>";
    }

    /**
     * Generates HTML content for a password change email with the provided password change link.
     *
     * @param link The password change link to be included in the email.
     * @return HTML content for the password change email.
     */
    public static String generatePasswordChangeEmailHtml(String link){
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\""+
                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">"+
                "<head>"+
                "    <!--[if gte mso 9]>"+
                "    <xml>"+
                "        <o:OfficeDocumentSettings>"+
                "            <o:AllowPNG/>"+
                "            <o:PixelsPerInch>96</o:PixelsPerInch>"+
                "        </o:OfficeDocumentSettings>"+
                "    </xml>"+
                "    <![endif]-->"+
                "    <meta content=\"text/html; charset=UTF-8\" http-equiv=\"Content-Type\">"+
                "    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">"+
                "    <meta name=\"x-apple-disable-message-reformatting\">"+
                "    <!--[if !mso]><!-->"+
                "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"><!--<![endif]-->"+
                "    <title></title>"+
                "    <style type=\"text/css\">"+
                "        @import url('https://fonts.googleapis.com/css2?family=Protest+Revolution&display=swap');"+
                "        @media only screen and (min-width: 620px) {"+
                "            .u-row {"+
                "                width: 600px !important;"+
                "            }"+
                "            .u-row .u-col {"+
                "                vertical-align: top;"+
                "            }"+
                "            .u-row .u-col-100 {"+
                "                width: 600px !important;"+
                "            }"+
                "        }"+
                "        @media (max-width: 620px) {"+
                "            .u-row-container {"+
                "                max-width: 100% !important;"+
                "                padding-left: 0px !important;"+
                "                padding-right: 0px !important;"+
                "            }"+
                "            .u-row .u-col {"+
                "                min-width: 320px !important;"+
                "                max-width: 100% !important;"+
                "                display: block !important;"+
                "            }"+
                "            .u-row {"+
                "                width: 100% !important;"+
                "            }"+
                "            .u-col {"+
                "                width: 100% !important;"+
                "            }"+
                "            .u-col > div {"+
                "                margin: 0 auto;"+
                "            }"+
                "        }"+
                "        body {"+
                "            margin: 0;"+
                "            padding: 0;"+
                "        }"+
                "        table,"+
                "        tr,"+
                "        td {"+
                "            vertical-align: top;"+
                "            border-collapse: collapse;"+
                "        }"+
                "        p {"+
                "            margin: 0;"+
                "        }"+
                "        .ie-container table,"+
                "        .mso-container table {"+
                "            table-layout: fixed;"+
                "        }"+
                "        * {"+
                "            line-height: inherit;"+
                "        }"+
                "        a[x-apple-data-detectors='true'] {"+
                "            color: inherit !important;"+
                "            text-decoration: none !important;"+
                "        }"+
                "        table, td {"+
                "            color: #000000;"+
                "        }"+
                "        #u_body a {"+
                "            color: #0000ee;"+
                "            text-decoration: underline;"+
                "        }"+
                "        @media (max-width: 480px) {"+
                "            #u_content_heading_4 .v-font-size {"+
                "                font-size: 22px !important;"+
                "            }"+
                "            #u_content_heading_4 .v-line-height {"+
                "                line-height: 170% !important;"+
                "            }"+
                "            #u_content_menu_1 .v-font-size {"+
                "                font-size: 11px !important;"+
                "            }"+
                "            #u_content_menu_1 .v-padding {"+
                "                padding: 0px 15px 5px !important;"+
                "            }"+
                "            #u_content_image_2 .v-container-padding-padding {"+
                "                padding: 30px 10px 10px !important;"+
                "            }"+
                "            #u_content_image_2 .v-src-width {"+
                "                width: auto !important;"+
                "            }"+
                "            #u_content_image_2 .v-src-max-width {"+
                "                max-width: 45% !important;"+
                "            }"+
                "            #u_content_heading_1 .v-font-size {"+
                "                font-size: 26px !important;"+
                "            }"+
                "            #u_content_heading_1 .v-line-height {"+
                "                line-height: 150% !important;"+
                "            }"+
                "            #u_content_text_1 .v-container-padding-padding {"+
                "                padding: 5px 10px 10px !important;"+
                "            }"+
                "            #u_content_button_1 .v-container-padding-padding {"+
                "                padding: 10px 10px 20px !important;"+
                "            }"+
                "            #u_content_button_1 .v-size-width {"+
                "                width: 65% !important;"+
                "            }"+
                "            #u_content_heading_3 .v-container-padding-padding {"+
                "                padding: 25px 19px 10px !important;"+
                "            }"+
                "            #u_content_heading_3 .v-font-size {"+
                "                font-size: 21px !important;"+
                "            }"+
                "            #u_content_heading_3 .v-text-align {"+
                "                text-align: center !important;"+
                "            }"+
                "            #u_content_heading_3 .v-line-height {"+
                "                line-height: 130% !important;"+
                "            }"+
                "            #u_content_text_4 .v-container-padding-padding {"+
                "                padding: 0px 20px 10px !important;"+
                "            }"+
                "            #u_content_text_4 .v-text-align {"+
                "                text-align: center !important;"+
                "            }"+
                "        }"+
                "    </style>"+
                "    <!--[if !mso]><!-->"+
                "    <link href=\"https://fonts.googleapis.com/css2?family=Protest+Revolution&display=swap\" rel=\"stylesheet\""+
                "          type=\"text/css\">"+
                "    <link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">"+
                "    <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">"+
                "    <!--<![endif]-->"+
                "</head>"+
                "<body class=\"clean-body u_body\""+
                "      style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">"+
                "<!--[if IE]>"+
                "<div class=\"ie-container\"><![endif]-->"+
                "<!--[if mso]>"+
                "<div class=\"mso-container\"><![endif]-->"+
                "<table cellpadding=\"0\""+
                "       cellspacing=\"0\""+
                "       id=\"u_body\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\">"+
                "    <tbody>"+
                "    <tr style=\"vertical-align: top\">"+
                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">"+
                "            <!--[if (mso)|(IE)]>"+
                "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                <tr>"+
                "                    <td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_heading_4\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <table width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td><![endif]-->"+
                "                                                <h1 class=\"v-text-align v-line-height v-font-size\""+
                "                                                    style=\"margin: 0px; line-height: 150%; text-align: center; font-family: 'Protest Revolution', sans-serif; font-size: 35px; color: #404040\">"+
                "                                                    <span style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\"><span"+
                "                                                            style=\"line-height: 37.4px;\">The Wasted Times</span></span></span></span></span></span></span></span></span></span></span></span></span>"+
                "                                                </h1>"+
                "                                                <!--[if mso]></td></tr></table><![endif]-->"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"598\""+
                "                            style=\"width: 598px;padding: 0px;border-top: 1px solid #ffffff;border-left: 1px solid #ffffff;border-right: 1px solid #ffffff;border-bottom: 1px solid #ffffff;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 1px solid #ffffff;border-left: 1px solid #ffffff;border-right: 1px solid #ffffff;border-bottom: 1px solid #ffffff;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_menu_1\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"menu\" style=\"text-align:center\">"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\""+
                "                                                           cellspacing=\"0\" align=\"center\">"+
                "                                                        <tr><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=" + link +
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        Home"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=\"https://www.unlayer.com\""+
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        Page"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=\"https://www.unlayer.com\""+
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        About US"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]>"+
                "                                                    <td style=\"padding:5px 15px\"><![endif]-->"+
                "                                                    <a class=\"v-padding v-font-size\" href=\"https://www.unlayer.com\""+
                "                                                       style=\"padding:5px 15px;display:inline-block;color:#485696;font-family:'Cabin',sans-serif;font-size:14px;text-decoration:none\""+
                "                                                       target=\"_self\">"+
                "                                                        Contact Us"+
                "                                                    </a>"+
                "                                                    <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                                                    <!--[if (mso)|(IE)]></tr></table><![endif]-->"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 10px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 10px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_image_2\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:81px 10px 10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td align=\"center\""+
                "                                                            class=\"v-text-align\""+
                "                                                            style=\"padding-right: 0px;padding-left: 0px;\">"+
                "                                                            <img align=\"center\" alt=\"image\" border=\"0\""+
                "                                                                 class=\"v-src-width v-src-max-width\" src=\"cid:lock.png\""+
                "                                                                 style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 42%;max-width: 243.6px;\""+
                "                                                                 title=\"image\" width=\"243.6\"/>"+
                "                                                        </td>"+
                "                                                    </tr>"+
                "                                                </table>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_heading_1\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 0px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <table width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td><![endif]-->"+
                "                                                <h1 class=\"v-text-align v-line-height v-font-size\""+
                "                                                    style=\"margin: 0px; color: #485696; line-height: 160%; text-align: center; word-wrap: break-word; font-size: 35px; font-weight: 400;\">"+
                "                                                    <span><span><span><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"><span style=\"line-height: 39px;\"><span"+
                "                                                            style=\"line-height: 39px;\"></span><strong>Change Password</strong></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span>"+
                "                                                </h1>"+
                "                                                <!--[if mso]></td></tr></table><![endif]-->"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_text_1\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 50px 10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"v-text-align v-line-height v-font-size\""+
                "                                                     style=\"font-size: 14px; color: #485696; line-height: 140%; text-align: center; word-wrap: break-word;\">"+
                "                                                    <p style=\"line-height: 140%;\"><span"+
                "                                                            data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiNjVKak1FdmhsNGVRclpybThWU3JnaSIsInBhc3RlSUQiOjQ0MjAzNDA0NywiZGF0YVR5cGUiOiJzY2VuZSJ9Cg==(/figmeta)-->\""+
                "                                                            style=\"line-height: 19.6px;\"></span>To ensure the security"+
                "                                                        of your account, click the link below to change your password"+
                "                                                        promptly. </p>"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_button_1\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 40px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <style>.v-button {"+
                "                                                    background: transparent !important;"+
                "                                                }</style><![endif]-->"+
                "                                                <div align=\"center\" class=\"v-text-align\">"+
                "                                                    <!--[if mso]>"+
                "                                                    <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\""+
                "                                                                 xmlns:w=\"urn:schemas-microsoft-com:office:word\""+
                "                                                                 href=\"https://unlayer.com\""+
                "                                                                 style=\"height:37px; v-text-anchor:middle; width:146px;\""+
                "                                                                 arcsize=\"11%\" stroke=\"f\" fillcolor=\"#e65815\">"+
                "                                                        <w:anchorlock/>"+
                "                                                        <center style=\"color:#ffffff;\"><![endif]-->"+
                "                                                    <a class=\"v-button v-size-width v-font-size\" href=\"" + link + "\""+
                "                                                       style=\"box-sizing: border-box;display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #e65815; border-radius: 4px;-webkit-border-radius: 4px; -moz-border-radius: 4px; width:auto; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;font-size: 14px;\""+
                "                                                       target=\"_blank\">"+
                "                                                        <span class=\"v-line-height v-padding\""+
                "                                                              style=\"display:block;padding:10px 20px;line-height:120%;\"><strong><span"+
                "                                                                style=\"line-height: 16.8px;\">Password Reset</span></strong></span>"+
                "                                                    </a>"+
                "                                                    <!--[if mso]></center></v:roundrect><![endif]-->"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                           role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:'Raleway',sans-serif;\">"+
                "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                                       height=\"0px\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 10px solid #ffffff;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\""+
                "                                                       width=\"100%\">"+
                "                                                    <tbody>"+
                "                                                    <tr style=\"vertical-align: top\">"+
                "                                                        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">"+
                "                                                            <span> </span>"+
                "                                                        </td>"+
                "                                                    </tr>"+
                "                                                    </tbody>"+
                "                                                </table>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">"+
                "                <div class=\"u-row\""+
                "                     style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">"+
                "                    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"+
                "                            <tr>"+
                "                                <td style=\"padding: 0px;background-color: transparent;\" align=\"center\">"+
                "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\">"+
                "                                        <tr style=\"background-color: transparent;\"><![endif]-->"+
                "                        <!--[if (mso)|(IE)]>"+
                "                        <td align=\"center\" width=\"600\""+
                "                            style=\"width: 600px;padding: 10px 10px 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\""+
                "                            valign=\"top\"><![endif]-->"+
                "                        <div class=\"u-col u-col-100\""+
                "                             style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">"+
                "                            <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                <!--[if (!mso)&(!IE)]><!-->"+
                "                                <div style=\"box-sizing: border-box; height: 100%; padding: 10px 10px 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">"+
                "                                    <!--<![endif]-->"+
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                           role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\">"+
                "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\""+
                "                                                       height=\"0px\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px dashed #485696;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\""+
                "                                                       width=\"100%\">"+
                "                                                    <tbody>"+
                "                                                    <tr style=\"vertical-align: top\">"+
                "                                                        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">"+
                "                                                            <span> </span>"+
                "                                                        </td>"+
                "                                                    </tr>"+
                "                                                    </tbody>"+
                "                                                </table>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_heading_3\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px 35px;font-family:'Raleway',sans-serif;\">"+
                "                                                <!--[if mso]>"+
                "                                                <table width=\"100%\">"+
                "                                                    <tr>"+
                "                                                        <td><![endif]-->"+
                "                                                <h1 class=\"v-text-align v-line-height v-font-size\""+
                "                                                    style=\"margin: 0px; color: #485696; line-height: 140%; text-align: center; word-wrap: break-word; font-size: 22px; font-weight: 400;\">"+
                "                                                    <span><span><span><span><span><span><span><span><span><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            style=\"line-height: 28.6px;\"><span"+
                "                                                            data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiMlBQSXNjNFd3Q2ZlU0tINFZVMlBXeiIsInBhc3RlSUQiOjk3MDc2NDM4NywiZGF0YVR5cGUiOiJzY2VuZSJ9Cg==(/figmeta)-->\""+
                "                                                            style=\"line-height: 28.6px;\"><strong>Thanks for the support!</strong></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span></span>"+
                "                                                </h1>"+
                "                                                <!--[if mso]></td></tr></table><![endif]-->"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <table border=\"0\" cellpadding=\"0\""+
                "                                           cellspacing=\"0\" id=\"u_content_text_4\" role=\"presentation\" style=\"font-family:'Raleway',sans-serif;\" width=\"100%\">"+
                "                                        <tbody>"+
                "                                        <tr>"+
                "                                            <td align=\"left\""+
                "                                                class=\"v-container-padding-padding\""+
                "                                                style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 60px 10px 35px;font-family:'Raleway',sans-serif;\">"+
                "                                                <div class=\"v-text-align v-line-height v-font-size\""+
                "                                                     style=\"font-size: 13px; color: #485696; line-height: 170%; text-align: center; word-wrap: break-word;\">"+
                "                                                    <p style=\"line-height: 170%;\"><span"+
                "                                                            data-metadata=\"<!--(figmeta)eyJmaWxlS2V5IjoiMlBQSXNjNFd3Q2ZlU0tINFZVMlBXeiIsInBhc3RlSUQiOjE5NDM5MjkyMjcsImRhdGFUeXBlIjoic2NlbmUifQo=(/figmeta)-->\""+
                "                                                            style=\"line-height: 22.1px;\"></span>For additional security"+
                "                                                        tips or if you have any questions, please visit our [Help"+
                "                                                        Center] or contact our support team at [Customer Support Email"+
                "                                                        or Phone Number]. </p>"+
                "                                                </div>"+
                "                                            </td>"+
                "                                        </tr>"+
                "                                        </tbody>"+
                "                                    </table>"+
                "                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->"+
                "                            </div>"+
                "                        </div>"+
                "                        <!--[if (mso)|(IE)]></td><![endif]-->"+
                "                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->"+
                "                    </div>"+
                "                </div>"+
                "            </div>"+
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->"+
                "        </td>"+
                "    </tr>"+
                "    </tbody>"+
                "</table>"+
                "<!--[if mso]></div><![endif]-->"+
                "<!--[if IE]></div><![endif]-->"+
                "</body>"+
                "</html>";
    }
}
