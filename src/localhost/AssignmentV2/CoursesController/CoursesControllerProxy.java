package localhost.AssignmentV2.CoursesController;

public class CoursesControllerProxy implements localhost.AssignmentV2.CoursesController.CoursesController_PortType {
  private String _endpoint = null;
  private localhost.AssignmentV2.CoursesController.CoursesController_PortType coursesController_PortType = null;
  
  public CoursesControllerProxy() {
    _initCoursesControllerProxy();
  }
  
  public CoursesControllerProxy(String endpoint) {
    _endpoint = endpoint;
    _initCoursesControllerProxy();
  }
  
  private void _initCoursesControllerProxy() {
    try {
      coursesController_PortType = (new localhost.AssignmentV2.CoursesController.CoursesController_ServiceLocator()).getCoursesControllerSOAP();
      if (coursesController_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)coursesController_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)coursesController_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (coursesController_PortType != null)
      ((javax.xml.rpc.Stub)coursesController_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public localhost.AssignmentV2.CoursesController.CoursesController_PortType getCoursesController_PortType() {
    if (coursesController_PortType == null)
      _initCoursesControllerProxy();
    return coursesController_PortType;
  }
  
  public java.lang.String newOperation(java.lang.String in) throws java.rmi.RemoteException{
    if (coursesController_PortType == null)
      _initCoursesControllerProxy();
    return coursesController_PortType.newOperation(in);
  }
  
  
}