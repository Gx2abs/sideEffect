// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimplePatientConditionDelegate.java

package properties;

import abstraction.SimpleDelegate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package properties:
//            PatientConditionDAO

public class SimplePatientConditionDelegate extends SimpleDelegate
{

    public SimplePatientConditionDelegate()
    {
    }

    public PatientConditionDAO getPatientConditionDAO()
    {
        return patientConditionDAO;
    }

    public void setPatientConditionDAO(PatientConditionDAO patientConditionDAO)
    {
        this.patientConditionDAO = patientConditionDAO;
    }

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        java.util.List list = patientConditionDAO.list(null);
        mav.setViewName("/view/jsp/properties/patientCondition.jsp");
        mav.addObject("list", list);
        return mav;
    }

    private PatientConditionDAO patientConditionDAO;
}
