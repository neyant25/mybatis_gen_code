package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_server_info")
public class TServerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 主机地址
     */
    private String ip;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 配置文件路径
     */
    @Column(name = "config_path")
    private String configPath;

    /**
     * 描述信息
     */
    private String remark;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取主机地址
     *
     * @return ip - 主机地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置主机地址
     *
     * @param ip 主机地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取端口号
     *
     * @return port - 端口号
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置端口号
     *
     * @param port 端口号
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取配置文件路径
     *
     * @return config_path - 配置文件路径
     */
    public String getConfigPath() {
        return configPath;
    }

    /**
     * 设置配置文件路径
     *
     * @param configPath 配置文件路径
     */
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    /**
     * 获取描述信息
     *
     * @return remark - 描述信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述信息
     *
     * @param remark 描述信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}