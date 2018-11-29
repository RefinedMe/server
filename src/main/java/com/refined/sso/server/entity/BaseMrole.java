package com.refined.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 *
 * @author zhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_mrole")
public class BaseMrole extends Model<BaseMrole> {

    private static final long serialVersionUID = -1145232763518716052L;
    private String uuid;
    private Date created;
    private Date updated;
    private String mrDescribe;
    private String mrName;
    private String mrId;
    private Date updstamp;


    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }
}
