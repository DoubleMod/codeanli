package com.code.anli.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @author masin
 * @since 2019-12-04
 */
@TableName("t_order")
public class TOrder extends Model<TOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("adzone_id")
    private Integer adzoneId;

    @TableField("adzone_name")
    private String adzoneName;

    @TableField("alimama_rate")
    private String alimamaRate;

    @TableField("alimama_share_fee")
    private String alimamaShareFee;

    @TableField("alipay_total_price")
    private String alipayTotalPrice;

    @TableField("app_key")
    private String appKey;

    @TableField("click_time")
    private String clickTime;

    @TableField("deposit_price")
    private String depositPrice;

    @TableField("flow_source")
    private String flowSource;

    @TableField("income_rate")
    private String incomeRate;

    @TableField("item_category_name")
    private String itemCategoryName;

    @TableField("item_id")
    private String itemId;

    @TableField("item_img")
    private String itemImg;

    @TableField("item_link")
    private String itemLink;

    @TableField("item_num")
    private Integer itemNum;

    @TableField("item_price")
    private String itemPrice;

    @TableField("item_title")
    private String itemTitle;

    @TableField("order_type")
    private String orderType;

    @TableField("pub_id")
    private Integer pubId;

    @TableField("pub_share_fee")
    private String pubShareFee;

    @TableField("pub_share_pre_fee")
    private String pubSharePreFee;

    @TableField("pub_share_rate")
    private String pubShareRate;

    @TableField("refund_tag")
    private Integer refundTag;

    @TableField("seller_nick")
    private String sellerNick;

    @TableField("seller_shop_title")
    private String sellerShopTitle;

    @TableField("site_id")
    private Integer siteId;

    @TableField("site_name")
    private String siteName;

    @TableField("subsidy_fee")
    private String subsidyFee;

    @TableField("subsidy_rate")
    private String subsidyRate;

    @TableField("subsidy_type")
    private String subsidyType;

    @TableField("tb_deposit_time")
    private String tbDepositTime;

    @TableField("tb_paid_time")
    private String tbPaidTime;

    @TableField("terminal_type")
    private String terminalType;

    @TableField("tk_commission_fee_for_media_platform")
    private String tkCommissionFeeForMediaPlatform;

    @TableField("tk_commission_pre_fee_for_media_platform")
    private String tkCommissionPreFeeForMediaPlatform;

    @TableField("tk_commission_rate_for_media_platform")
    private String tkCommissionRateForMediaPlatform;

    @TableField("tk_create_time")
    private String tkCreateTime;

    @TableField("tk_deposit_time")
    private String tkDepositTime;

    @TableField("tk_order_role")
    private Integer tkOrderRole;

    @TableField("tk_paid_time")
    private String tkPaidTime;

    @TableField("tk_status")
    private Integer tkStatus;

    @TableField("tk_total_rate")
    private String tkTotalRate;

    @TableField("total_commission_fee")
    private String totalCommissionFee;

    @TableField("total_commission_rate")
    private String totalCommissionRate;

    @TableField("trade_id")
    private String tradeId;

    @TableField("trade_parent_id")
    private String tradeParentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdzoneId() {
        return adzoneId;
    }

    public void setAdzoneId(Integer adzoneId) {
        this.adzoneId = adzoneId;
    }

    public String getAdzoneName() {
        return adzoneName;
    }

    public void setAdzoneName(String adzoneName) {
        this.adzoneName = adzoneName;
    }

    public String getAlimamaRate() {
        return alimamaRate;
    }

    public void setAlimamaRate(String alimamaRate) {
        this.alimamaRate = alimamaRate;
    }

    public String getAlimamaShareFee() {
        return alimamaShareFee;
    }

    public void setAlimamaShareFee(String alimamaShareFee) {
        this.alimamaShareFee = alimamaShareFee;
    }

    public String getAlipayTotalPrice() {
        return alipayTotalPrice;
    }

    public void setAlipayTotalPrice(String alipayTotalPrice) {
        this.alipayTotalPrice = alipayTotalPrice;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

    public String getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(String depositPrice) {
        this.depositPrice = depositPrice;
    }

    public String getFlowSource() {
        return flowSource;
    }

    public void setFlowSource(String flowSource) {
        this.flowSource = flowSource;
    }

    public String getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(String incomeRate) {
        this.incomeRate = incomeRate;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public String getPubShareFee() {
        return pubShareFee;
    }

    public void setPubShareFee(String pubShareFee) {
        this.pubShareFee = pubShareFee;
    }

    public String getPubSharePreFee() {
        return pubSharePreFee;
    }

    public void setPubSharePreFee(String pubSharePreFee) {
        this.pubSharePreFee = pubSharePreFee;
    }

    public String getPubShareRate() {
        return pubShareRate;
    }

    public void setPubShareRate(String pubShareRate) {
        this.pubShareRate = pubShareRate;
    }

    public Integer getRefundTag() {
        return refundTag;
    }

    public void setRefundTag(Integer refundTag) {
        this.refundTag = refundTag;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getSellerShopTitle() {
        return sellerShopTitle;
    }

    public void setSellerShopTitle(String sellerShopTitle) {
        this.sellerShopTitle = sellerShopTitle;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSubsidyFee() {
        return subsidyFee;
    }

    public void setSubsidyFee(String subsidyFee) {
        this.subsidyFee = subsidyFee;
    }

    public String getSubsidyRate() {
        return subsidyRate;
    }

    public void setSubsidyRate(String subsidyRate) {
        this.subsidyRate = subsidyRate;
    }

    public String getSubsidyType() {
        return subsidyType;
    }

    public void setSubsidyType(String subsidyType) {
        this.subsidyType = subsidyType;
    }

    public String getTbDepositTime() {
        return tbDepositTime;
    }

    public void setTbDepositTime(String tbDepositTime) {
        this.tbDepositTime = tbDepositTime;
    }

    public String getTbPaidTime() {
        return tbPaidTime;
    }

    public void setTbPaidTime(String tbPaidTime) {
        this.tbPaidTime = tbPaidTime;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTkCommissionFeeForMediaPlatform() {
        return tkCommissionFeeForMediaPlatform;
    }

    public void setTkCommissionFeeForMediaPlatform(String tkCommissionFeeForMediaPlatform) {
        this.tkCommissionFeeForMediaPlatform = tkCommissionFeeForMediaPlatform;
    }

    public String getTkCommissionPreFeeForMediaPlatform() {
        return tkCommissionPreFeeForMediaPlatform;
    }

    public void setTkCommissionPreFeeForMediaPlatform(String tkCommissionPreFeeForMediaPlatform) {
        this.tkCommissionPreFeeForMediaPlatform = tkCommissionPreFeeForMediaPlatform;
    }

    public String getTkCommissionRateForMediaPlatform() {
        return tkCommissionRateForMediaPlatform;
    }

    public void setTkCommissionRateForMediaPlatform(String tkCommissionRateForMediaPlatform) {
        this.tkCommissionRateForMediaPlatform = tkCommissionRateForMediaPlatform;
    }

    public String getTkCreateTime() {
        return tkCreateTime;
    }

    public void setTkCreateTime(String tkCreateTime) {
        this.tkCreateTime = tkCreateTime;
    }

    public String getTkDepositTime() {
        return tkDepositTime;
    }

    public void setTkDepositTime(String tkDepositTime) {
        this.tkDepositTime = tkDepositTime;
    }

    public Integer getTkOrderRole() {
        return tkOrderRole;
    }

    public void setTkOrderRole(Integer tkOrderRole) {
        this.tkOrderRole = tkOrderRole;
    }

    public String getTkPaidTime() {
        return tkPaidTime;
    }

    public void setTkPaidTime(String tkPaidTime) {
        this.tkPaidTime = tkPaidTime;
    }

    public Integer getTkStatus() {
        return tkStatus;
    }

    public void setTkStatus(Integer tkStatus) {
        this.tkStatus = tkStatus;
    }

    public String getTkTotalRate() {
        return tkTotalRate;
    }

    public void setTkTotalRate(String tkTotalRate) {
        this.tkTotalRate = tkTotalRate;
    }

    public String getTotalCommissionFee() {
        return totalCommissionFee;
    }

    public void setTotalCommissionFee(String totalCommissionFee) {
        this.totalCommissionFee = totalCommissionFee;
    }

    public String getTotalCommissionRate() {
        return totalCommissionRate;
    }

    public void setTotalCommissionRate(String totalCommissionRate) {
        this.totalCommissionRate = totalCommissionRate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeParentId() {
        return tradeParentId;
    }

    public void setTradeParentId(String tradeParentId) {
        this.tradeParentId = tradeParentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
