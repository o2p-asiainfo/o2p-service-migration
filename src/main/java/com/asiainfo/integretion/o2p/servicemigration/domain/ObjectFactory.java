package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _TransformerScriptSrc_QNAME = new QName("", "scriptSrc");
    private final static QName _TransformerAdapterType_QNAME = new QName("", "adapterType");
    private final static QName _TransformerTransformerCode_QNAME = new QName("", "transformerCode");
    private final static QName _TransformerAdapterName_QNAME = new QName("", "adapterName");
    private final static QName _TransformerNodeValueAdapterReq_QNAME = new QName("", "nodeValueAdapterReq");
    private final static QName _TransformerCreateDate_QNAME = new QName("", "createDate");
    private final static QName _TransformerNodeDescMaper_QNAME = new QName("", "nodeDescMaper");
    private final static QName _TransformerTargetContractFormat_QNAME = new QName("", "targetContractFormat");
    private final static QName _ServiceObjects_QNAME = new QName("", "serviceObjects");
    private final static QName _ComsumerComponent_QNAME = new QName("", "comsumerComponent");
    private final static QName _Status_QNAME = new QName("", "status");
    private final static QName _Tenant_QNAME = new QName("", "tenant");
    private final static QName _Contract_QNAME = new QName("", "contract");
    private final static QName _ContractVersion_QNAME = new QName("", "contractVersion");
    private final static QName _Org_QNAME = new QName("", "org");
    private final static QName _RequestContractFormat_QNAME = new QName("", "requestContractFormat");
    private final static QName _Endpoint_QNAME = new QName("", "endpoint");
    private final static QName _AuthenticationExpress_QNAME = new QName("", "authenticationExpress");
    private final static QName _TechnologyImplement_QNAME = new QName("", "technologyImplement");
    private final static QName _ProducerComponent_QNAME = new QName("", "producerComponent");
    private final static QName _NodeDesc_QNAME = new QName("", "nodeDesc");
    private final static QName _ResponceContractFormat_QNAME = new QName("", "responceContractFormat");
    private final static QName _MessageFlow_QNAME = new QName("", "messageFlow");
    private final static QName _ContractDocument_QNAME = new QName("", "contractDocument");
    private final static QName _ApiInvokeObjectFlowControl_QNAME = new QName("", "apiInvokeObjectFlowControl");
    private final static QName _DataTypeDataTypeCode_QNAME = new QName("", "dataTypeCode");
    private final static QName _DataTypeDataTypeName_QNAME = new QName("", "dataTypeName");
    private final static QName _DataTypeDataTypeDesc_QNAME = new QName("", "dataTypeDesc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.asiainfo.integretion.o2p.servicemigration.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Filepublish }
     * 
     */
    public Filepublish createFilepublish() {
        return new Filepublish();
    }

    /**
     * Create an instance of {@link GetValueExpr }
     * 
     */
    public GetValueExpr createGetValueExpr() {
        return new GetValueExpr();
    }

    /**
     * Create an instance of {@link Service }
     * 
     */
    public Service createServiceType() {
        return new Service();
    }

    /**
     * Create an instance of {@link ContractVersion }
     * 
     */
    public ContractVersion createContractVersionType() {
        return new ContractVersion();
    }

    /**
     * Create an instance of {@link NodeDesc }
     * 
     */
    public NodeDesc createNodeDescType() {
        return new NodeDesc();
    }

    /**
     * Create an instance of {@link HostConfiguration }
     * 
     */
    public HostConfiguration createHostConfiguration() {
        return new HostConfiguration();
    }

    /**
     * Create an instance of {@link PLUGINSCALL }
     * 
     */
    public PLUGINSCALL createPLUGINSCALL() {
        return new PLUGINSCALL();
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponentType() {
        return new Component();
    }

    /**
     * Create an instance of {@link AttrValue }
     * 
     */
    public AttrValue createAttrValue() {
        return new AttrValue();
    }

    /**
     * Create an instance of {@link App }
     * 
     */
    public App createApp() {
        return new App();
    }

    /**
     * Create an instance of {@link DataType }
     * 
     */
    public DataType createDataType() {
        return new DataType();
    }

    /**
     * Create an instance of {@link Filemove }
     * 
     */
    public Filemove createFilemove() {
        return new Filemove();
    }
    
    public EndpointChoice createEndpointChoice() {
    	return new EndpointChoice();
    }
    
    public ContractFormatChoice createContractChoice() {
    	return  new ContractFormatChoice();
    }

    /**
     * Create an instance of {@link EndpointSpec }
     * 
     */
    public EndpointSpec createEndpointSpecType() {
        return new EndpointSpec();
    }

    /**
     * Create an instance of {@link FLATFILEREADER }
     * 
     */
    public FLATFILEREADER createFLATFILEREADER() {
        return new FLATFILEREADER();
    }

    /**
     * Create an instance of {@link TechnicalImplementationControlStrategy }
     * 
     */
    public TechnicalImplementationControlStrategy createTechnicalImplementationControlStrategy() {
        return new TechnicalImplementationControlStrategy();
    }

    /**
     * Create an instance of {@link Download }
     * 
     */
    public Download createDownload() {
        return new Download();
    }

    /**
     * Create an instance of {@link FlowControlPolicy }
     * 
     */
    public FlowControlPolicy createFlowControlPolicyType() {
        return new FlowControlPolicy();
    }

    /**
     * Create an instance of {@link ContractFormat }
     * 
     */
    public ContractFormat createContractFormatType() {
        return new ContractFormat();
    }

    /**
     * Create an instance of {@link RemoteHostAuthentication }
     * 
     */
    public RemoteHostAuthentication createRemoteHostAuthentication() {
        return new RemoteHostAuthentication();
    }

    /**
     * Create an instance of {@link ServiceCatalog }
     * 
     */
    public ServiceCatalog createServiceCatalog() {
        return new ServiceCatalog();
    }

    /**
     * Create an instance of {@link NodeDescMaper }
     * 
     */
    public NodeDescMaper createNodeDescMaper() {
        return new NodeDescMaper();
    }

    /**
     * Create an instance of {@link RouteCondition }
     * 
     */
    public RouteCondition createRouteCondition() {
        return new RouteCondition();
    }

    /**
     * Create an instance of {@link XMLFILEREADER }
     * 
     */
    public XMLFILEREADER createXMLFILEREADER() {
        return new XMLFILEREADER();
    }

    /**
     * Create an instance of {@link RoutePolicy }
     * 
     */
    public RoutePolicy createRoutePolicy() {
        return new RoutePolicy();
    }

    /**
     * Create an instance of {@link FETCHFILE }
     * 
     */
    public FETCHFILE createFETCHFILE() {
        return new FETCHFILE();
    }

    /**
     * Create an instance of {@link TechnologyImplementFlowControl }
     * 
     */
    public TechnologyImplementFlowControl createTechnologyImplementFlowControlType() {
        return new TechnologyImplementFlowControl();
    }

    /**
     * Create an instance of {@link VALIDATE }
     * 
     */
    public VALIDATE createVALIDATE() {
        return new VALIDATE();
    }

    /**
     * Create an instance of {@link ApiInvokeObject }
     * 
     */
    public ApiInvokeObject createApiInvokeObject() {
        return new ApiInvokeObject();
    }

    /**
     * Create an instance of {@link ZIPFILE }
     * 
     */
    public ZIPFILE createZIPFILE() {
        return new ZIPFILE();
    }

    /**
     * Create an instance of {@link FileRelocationServiceInvocationInstance }
     * 
     */
    public FileRelocationServiceInvocationInstance createFileRelocationServiceInvocationInstance() {
        return new FileRelocationServiceInvocationInstance();
    }

    /**
     * Create an instance of {@link Transformer }
     * 
     */
    public Transformer createTransformer() {
        return new Transformer();
    }

    /**
     * Create an instance of {@link Component.Org }
     * 
     */
    public Org createComponentTypeOrg() {
        return new Org();
    }

    /**
     * Create an instance of {@link Endpoint }
     * 
     */
    public Endpoint createEndpointType() {
        return new Endpoint();
    }

    /**
     * Create an instance of {@link MessageFlow }
     * 
     */
    public MessageFlow createMessageFlowType() {
        return new MessageFlow();
    }

    /**
     * Create an instance of {@link Tenant }
     * 
     */
    public Tenant createTenantType() {
        return new Tenant();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContractType() {
        return new Contract();
    }

    /**
     * Create an instance of {@link AuthenticationExpress }
     * 
     */
    public AuthenticationExpress createAuthenticationExpressType() {
        return new AuthenticationExpress();
    }

    /**
     * Create an instance of {@link TechnologyImplement }
     * 
     */
    public TechnologyImplement createTechnologyImplementType() {
        return new TechnologyImplement();
    }

    /**
     * Create an instance of {@link Api.TechnologyImplements }
     * 
     */
    public TechnologyImplement createTechnologyImplement() {
        return new TechnologyImplement();
    }

    /**
     * Create an instance of {@link ApiInvokeObjectFlowControl }
     * 
     */
    public ApiInvokeObjectFlowControl createApiInvokeObjectFlowControlType() {
        return new ApiInvokeObjectFlowControl();
    }

    /**
     * Create an instance of {@link ServiceObject }
     * 
     */
    public ServiceObject createServiceObjectType() {
        return new ServiceObject();
    }

    /**
     * Create an instance of {@link NodeValueAdapterReq }
     * 
     */
    public NodeValueAdapterReq createNodeValueAdapterReq() {
        return new NodeValueAdapterReq();
    }

    /**
     * Create an instance of {@link ContractDocument }
     * 
     */
    public ContractDocument createContractDocumentType() {
        return new ContractDocument();
    }

    /**
     * Create an instance of {@link VariableMap }
     * 
     */
    public VariableMap createVariableMap() {
        return new VariableMap();
    }

    /**
     * Create an instance of {@link Contract2AttrSpec }
     * 
     */
    public Contract2AttrSpec createContract2AttrSpec() {
        return new Contract2AttrSpec();
    }

    /**
     * Create an instance of {@link Org }
     * 
     */
    public Org createOrgType() {
        return new Org();
    }

    /**
     * Create an instance of {@link FileDirectory }
     * 
     */
    public FileDirectory createFileDirectory() {
        return new FileDirectory();
    }

    /**
     * Create an instance of {@link ContractFormat }
     * 
     */
    public ContractFormat createContractFormatDetailType() {
        return new ContractFormat();
    }

    /**
     * Create an instance of {@link TransScript2Param }
     * 
     */
    public TransScript2Param createTransScript2Param() {
        return new TransScript2Param();
    }

    /**
     * Create an instance of {@link JDBCINOUT }
     * 
     */
    public JDBCINOUT createJDBCINOUT() {
        return new JDBCINOUT();
    }

    /**
     * Create an instance of {@link Call }
     * 
     */
    public Call createCall() {
        return new Call();
    }

    /**
     * Create an instance of {@link RuleStrategy }
     * 
     */
    public RuleStrategy createRuleStrategy() {
        return new RuleStrategy();
    }

    /**
     * Create an instance of {@link Filesubscribe }
     * 
     */
    public Filesubscribe createFilesubscribe() {
        return new Filesubscribe();
    }

    /**
     * Create an instance of {@link Api }
     * 
     */
    public Api createApiType() {
        return new Api();
    }

    /**
     * Create an instance of {@link ServiceRouteConfig }
     * 
     */
    public ServiceRouteConfig createServiceRouteConfig() {
        return new ServiceRouteConfig();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "scriptSrc", scope = Transformer.class)
    public JAXBElement<String> createTransformerScriptSrc(String value) {
        return new JAXBElement<String>(_TransformerScriptSrc_QNAME, String.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "adapterType", scope = Transformer.class)
    public JAXBElement<BigInteger> createTransformerAdapterType(BigInteger value) {
        return new JAXBElement<BigInteger>(_TransformerAdapterType_QNAME, BigInteger.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "transformerCode", scope = Transformer.class)
    public JAXBElement<String> createTransformerTransformerCode(String value) {
        return new JAXBElement<String>(_TransformerTransformerCode_QNAME, String.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "adapterName", scope = Transformer.class)
    public JAXBElement<String> createTransformerAdapterName(String value) {
        return new JAXBElement<String>(_TransformerAdapterName_QNAME, String.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeValueAdapterReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nodeValueAdapterReq", scope = Transformer.class)
    public JAXBElement<NodeValueAdapterReq> createTransformerNodeValueAdapterReq(NodeValueAdapterReq value) {
        return new JAXBElement<NodeValueAdapterReq>(_TransformerNodeValueAdapterReq_QNAME, NodeValueAdapterReq.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "createDate", scope = Transformer.class)
    public JAXBElement<XMLGregorianCalendar> createTransformerCreateDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TransformerCreateDate_QNAME, XMLGregorianCalendar.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeDescMaper }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nodeDescMaper", scope = Transformer.class)
    public JAXBElement<NodeDescMaper> createTransformerNodeDescMaper(NodeDescMaper value) {
        return new JAXBElement<NodeDescMaper>(_TransformerNodeDescMaper_QNAME, NodeDescMaper.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractFormat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "targetContractFormat", scope = Transformer.class)
    public JAXBElement<ContractFormat> createTransformerTargetContractFormat(ContractFormat value) {
        return new JAXBElement<ContractFormat>(_TransformerTargetContractFormat_QNAME, ContractFormat.class, Transformer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Component }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "comsumerComponent")
    public JAXBElement<Component> createComsumerComponent(Component value) {
        return new JAXBElement<Component>(_ComsumerComponent_QNAME, Component.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tenant }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "tenant")
    public JAXBElement<Tenant> createTenant(Tenant value) {
        return new JAXBElement<Tenant>(_Tenant_QNAME, Tenant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Contract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "contract")
    public JAXBElement<Contract> createContract(Contract value) {
        return new JAXBElement<Contract>(_Contract_QNAME, Contract.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "contractVersion")
    public JAXBElement<ContractVersion> createContractVersion(ContractVersion value) {
        return new JAXBElement<ContractVersion>(_ContractVersion_QNAME, ContractVersion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Org }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "org")
    public JAXBElement<Org> createOrg(Org value) {
        return new JAXBElement<Org>(_Org_QNAME, Org.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractFormat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "requestContractFormat")
    public JAXBElement<ContractFormat> createRequestContractFormat(ContractFormat value) {
        return new JAXBElement<ContractFormat>(_RequestContractFormat_QNAME, ContractFormat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Endpoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "endpoint")
    public JAXBElement<Endpoint> createEndpoint(Endpoint value) {
        return new JAXBElement<Endpoint>(_Endpoint_QNAME, Endpoint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationExpress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "authenticationExpress")
    public JAXBElement<AuthenticationExpress> createAuthenticationExpress(AuthenticationExpress value) {
        return new JAXBElement<AuthenticationExpress>(_AuthenticationExpress_QNAME, AuthenticationExpress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TechnologyImplement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "technologyImplement")
    public JAXBElement<TechnologyImplement> createTechnologyImplement(TechnologyImplement value) {
        return new JAXBElement<TechnologyImplement>(_TechnologyImplement_QNAME, TechnologyImplement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Component }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "producerComponent")
    public JAXBElement<Component> createProducerComponent(Component value) {
        return new JAXBElement<Component>(_ProducerComponent_QNAME, Component.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeDesc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nodeDesc")
    public JAXBElement<NodeDesc> createNodeDesc(NodeDesc value) {
        return new JAXBElement<NodeDesc>(_NodeDesc_QNAME, NodeDesc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractFormat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "responceContractFormat")
    public JAXBElement<ContractFormat> createResponceContractFormat(ContractFormat value) {
        return new JAXBElement<ContractFormat>(_ResponceContractFormat_QNAME, ContractFormat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageFlow }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "messageFlow")
    public JAXBElement<MessageFlow> createMessageFlow(MessageFlow value) {
        return new JAXBElement<MessageFlow>(_MessageFlow_QNAME, MessageFlow.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "contractDocument")
    public JAXBElement<ContractDocument> createContractDocument(ContractDocument value) {
        return new JAXBElement<ContractDocument>(_ContractDocument_QNAME, ContractDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiInvokeObjectFlowControl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "apiInvokeObjectFlowControl")
    public JAXBElement<ApiInvokeObjectFlowControl> createApiInvokeObjectFlowControl(ApiInvokeObjectFlowControl value) {
        return new JAXBElement<ApiInvokeObjectFlowControl>(_ApiInvokeObjectFlowControl_QNAME, ApiInvokeObjectFlowControl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dataTypeCode", scope = DataType.class)
    public JAXBElement<String> createDataTypeDataTypeCode(String value) {
        return new JAXBElement<String>(_DataTypeDataTypeCode_QNAME, String.class, DataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dataTypeName", scope = DataType.class)
    public JAXBElement<String> createDataTypeDataTypeName(String value) {
        return new JAXBElement<String>(_DataTypeDataTypeName_QNAME, String.class, DataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dataTypeDesc", scope = DataType.class)
    public JAXBElement<String> createDataTypeDataTypeDesc(String value) {
        return new JAXBElement<String>(_DataTypeDataTypeDesc_QNAME, String.class, DataType.class, value);
    }

}
