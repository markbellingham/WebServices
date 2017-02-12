/**
 * CoursesController_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.AssignmentV2.CoursesController;

public class CoursesController_ServiceLocator extends org.apache.axis.client.Service implements localhost.AssignmentV2.CoursesController.CoursesController_Service {

    public CoursesController_ServiceLocator() {
    }


    public CoursesController_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CoursesController_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CoursesControllerSOAP
    private java.lang.String CoursesControllerSOAP_address = "http://localhost:8080/AssignmentV2/";

    public java.lang.String getCoursesControllerSOAPAddress() {
        return CoursesControllerSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CoursesControllerSOAPWSDDServiceName = "CoursesControllerSOAP";

    public java.lang.String getCoursesControllerSOAPWSDDServiceName() {
        return CoursesControllerSOAPWSDDServiceName;
    }

    public void setCoursesControllerSOAPWSDDServiceName(java.lang.String name) {
        CoursesControllerSOAPWSDDServiceName = name;
    }

    public localhost.AssignmentV2.CoursesController.CoursesController_PortType getCoursesControllerSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CoursesControllerSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCoursesControllerSOAP(endpoint);
    }

    public localhost.AssignmentV2.CoursesController.CoursesController_PortType getCoursesControllerSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.AssignmentV2.CoursesController.CoursesControllerSOAPStub _stub = new localhost.AssignmentV2.CoursesController.CoursesControllerSOAPStub(portAddress, this);
            _stub.setPortName(getCoursesControllerSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCoursesControllerSOAPEndpointAddress(java.lang.String address) {
        CoursesControllerSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.AssignmentV2.CoursesController.CoursesController_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.AssignmentV2.CoursesController.CoursesControllerSOAPStub _stub = new localhost.AssignmentV2.CoursesController.CoursesControllerSOAPStub(new java.net.URL(CoursesControllerSOAP_address), this);
                _stub.setPortName(getCoursesControllerSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CoursesControllerSOAP".equals(inputPortName)) {
            return getCoursesControllerSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/AssignmentV2/CoursesController/", "CoursesController");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/AssignmentV2/CoursesController/", "CoursesControllerSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CoursesControllerSOAP".equals(portName)) {
            setCoursesControllerSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
