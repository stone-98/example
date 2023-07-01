package com.ocat.example.mail.service;

/**
 * @author shikui@tiduyun.com
 * @date 2023/4/26
 */

import com.ocat.example.mail.model.MailSendDto;
import java.util.Map;

/**
 * @description: 邮件服务Service
 * @author: stone-98
 * @create: 2021/1/21 10:06
 **/

public interface MailService {
    /**
     * 发送普通邮件
     *
     * @param dto
     */
    void sendSimpleMailMessage(MailSendDto dto);

    /**
     * 发送普通邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMailMessage(String to, String subject, String content);


    /**
     * 发送 HTML 邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendMimeMessage(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     */
    void sendMimeMessage(String to, String subject, String content, String filePath);

    /**
     * 发送带静态文件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param rscIdMap 需要替换的静态文件
     */
    void sendMimeMessage(String to, String subject, String content, Map<String, String> rscIdMap);
}

