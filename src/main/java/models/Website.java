package models;

/**
 * Created by DuongKK on 12/10/2017.
 */
public class Website {
    String host;
    String urlQuery;
    String id;
    String configUrl;
    String nodeParent;
    String nodeImg;
    String nodeUrlDetail;
    String nodeName;
    String nodePricing;
    String nodeList;

    public Website(String host, String urlQuery, String configUrl,String nodeList, String nodeParent, String nodeImg, String nodeUrlDetail, String nodeName, String nodePricing) {
        this.host = host;
        this.nodeList =nodeList;
        this.urlQuery = urlQuery;
        this.configUrl = configUrl;
        this.nodeParent = nodeParent;
        this.nodeImg = nodeImg;
        this.nodeUrlDetail = nodeUrlDetail;
        this.nodeName = nodeName;
        this.nodePricing = nodePricing;
    }

    public String getNodeParent() {
        return nodeParent;
    }

    public void setNodeParent(String nodeParent) {
        this.nodeParent = nodeParent;
    }

    public String getNodeList() {
        return nodeList;
    }

    public void setNodeList(String nodeList) {
        this.nodeList = nodeList;
    }

    public String getNodeImg() {
        return nodeImg;
    }

    public void setNodeImg(String nodeImg) {
        this.nodeImg = nodeImg;
    }

    public String getNodeUrlDetail() {
        return nodeUrlDetail;
    }

    public void setNodeUrlDetail(String nodeUrlDetail) {
        this.nodeUrlDetail = nodeUrlDetail;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodePricing() {
        return nodePricing;
    }

    public void setNodePricing(String nodePricing) {
        this.nodePricing = nodePricing;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlQuery() {
        return urlQuery;
    }

    public String getConfigUrl() {
        return configUrl;
    }

    public void setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
    }

    public void setUrlQuery(String urlQuery) {
        this.urlQuery = urlQuery;
    }
}
