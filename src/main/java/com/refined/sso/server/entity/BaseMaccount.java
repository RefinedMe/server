package com.refined.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author zhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_maccount")
public class BaseMaccount extends Model<BaseMaccount> {

    private static final long serialVersionUID = 8728949213853075423L;
    private String uuid;
    private Date created;
    private Date updated;
    private String maAid;
    private String maAlias;
    private String maEnable;
    private String maGroup;
    private Date maLastlogin;
    private String maMail;
    private String maMno;
    private String maMobile;
    private String maName;
    private String maPassword;
    private String maPicture;
    private String maType;
    private String maRemind;
    @TableField("ma_payID")
    private String maPayId;
    @TableField("ma_payName")
    private String maPayName;
    @TableField("ma_payPassword")
    private String maPayPassword;
    private String maFirstName;
    private String maLastName;
    private Date updstamp;
    @TableField("createPid")
    private String createPid;
    @TableField("lastEditPid")
    private String lastEditPid;
    private String eid;
    private String ismail;
    private Date maildate;
    private String isread;
    private String isphone;


    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }
}
