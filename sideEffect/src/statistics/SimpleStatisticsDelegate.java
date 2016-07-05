// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleStatisticsDelegate.java

package statistics;

import abstraction.IDValuePair;
import abstraction.SimpleDelegate;
import com.tobesoft.xplatform.data.*;
import com.tobesoft.xplatform.tx.HttpPlatformRequest;
import com.tobesoft.xplatform.tx.PlatformException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import member.MemberDAO;
import org.springframework.web.servlet.ModelAndView;
import properties.*;
import properties.country.SimpleCountry;
import report.SimpleSideeffectReport;
import report.SimpleSideeffectReportDAO;
import safety.SafetyDAO;
import safety.SimpleSafetyReport;
import safety.renewal.sgi.category.*;
import safety.renewal.sgi.item.*;
import sideeffect.SimpleSideeffectCause;
import sideeffect.SimpleSideeffectResult;

public class SimpleStatisticsDelegate extends SimpleDelegate
{

    public SimpleStatisticsDelegate()
    {
    }

    public PropertiesDAO getPropertiesDAO()
    {
        return propertiesDAO;
    }

    public void setPropertiesDAO(PropertiesDAO propertiesDAO)
    {
        this.propertiesDAO = propertiesDAO;
    }

    public ItemCategoryDAO getItemCategoryDAO()
    {
        return itemCategoryDAO;
    }

    public void setItemCategoryDAO(ItemCategoryDAO itemCategoryDAO)
    {
        this.itemCategoryDAO = itemCategoryDAO;
    }

    public SimpleSideeffectReportDAO getSideEffectDAO()
    {
        return sideEffectDAO;
    }

    public void setSideEffectDAO(SimpleSideeffectReportDAO sideEffectDAO)
    {
        this.sideEffectDAO = sideEffectDAO;
    }

    public SafetyDAO getSafetyDAO()
    {
        return safetyDAO;
    }

    public void setSafetyDAO(SafetyDAO safetyDAO)
    {
        this.safetyDAO = safetyDAO;
    }

    public ItemDAO getItemDAO()
    {
        return itemDAO;
    }

    public void setItemDAO(ItemDAO itemDAO)
    {
        this.itemDAO = itemDAO;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/statistics/statisticsLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        return mav;
    }

    public MemberDAO getMemberDAO()
    {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO)
    {
        this.memberDAO = memberDAO;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav)
    {
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/common/defaultLeft.jsp");
        mav.addObject("rightName", "/view/jsp/common/defaultRight.jsp");
        return mav;
    }

    public ModelAndView reporterType(HttpServletRequest request, HttpServletResponse response)
        throws UnsupportedEncodingException
    {
        List reporterTypes = propertiesDAO.list(SimpleReporterType.class);
        ModelAndView mav = null;
        mav = new ModelAndView();
        mav.addObject("codeList", reporterTypes);
        mav.addObject("dataSet", "ds_reporterTypes");
        mav.setViewName("/view/jsp/statistics/popReporterType.jsp");
        setDefaultViewSet(mav);
        return mav;
    }

    public ModelAndView reportPopupCodeList(HttpServletRequest request, HttpServletResponse response)
        throws PlatformException
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        String colName = "";
        String joinTable = "";
        String orderColName = "";
        String selectGB = Function.nullChk(request.getParameter("selectGB"), "");
        String reportType = Function.nullChk(request.getParameter("reportType"), "");
        String fmYY = Function.nullChk(request.getParameter("fmYY"), "");
        String fmMM = Function.nullChk(request.getParameter("fmMM"), "");
        String toYY = Function.nullChk(request.getParameter("toYY"), "");
        String toMM = Function.nullChk(request.getParameter("toMM"), "");
        String level = Function.nullChk(request.getParameter("level"), "");
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String popupGB = Function.nullChk(in_vl.getString("popupGB"), "N");
        String reporterTypes = "";
        DataSet ds_reporterTypes = i_xpData.getDataSet("ds_reporterTypes");
        try
        {
            Integer ds_chk = Integer.valueOf(0);
            for(int i = 0; i < ds_reporterTypes.getRowCount(); i++)
            {
                String check_yn = dsGet(ds_reporterTypes, i, "CHECK_YN");
                if(check_yn.equals("1"))
                {
                    ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
                    reporterTypes = (new StringBuilder(String.valueOf(reporterTypes))).append(dsGet(ds_reporterTypes, i, "ID")).append(",").toString();
                }
            }

            if(ds_chk.intValue() > 0)
                reporterTypes = reporterTypes.substring(0, reporterTypes.length() - 1);
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        System.out.println();
        if(popupGB.equals("popMeaClassNo"))
        {
            orderColName = "class_kor_name";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.ITEM_CATEGORY_NUMBER";
                joinTable = "join SGI_SE_ITEM junc on (report.MEB_ITEM_ID = junc.id) ";
                codeList = itemCategoryDAO.sideeffectMeaClassNo(colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc.ITEM_CATEGORY_NUMBER";
                joinTable = "join SGI_SE_ITEM junc on (report.MEB_ITEM_ID = junc.id) ";
                codeList = itemCategoryDAO.safetyMeaClassNo(colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popItemCategory.jsp");
        } else
        if(popupGB.equals("popMedical"))
        {
            orderColName = "fdaSourcePtKor";
            List codeList = null;
            SimpleMedicalDeviceMalfunctionCode parentCode = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.medical_id";
                joinTable = "join JUNC_SIDE_EFFECT_MEDICAL_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectCodeList(SimpleMedicalDeviceMalfunctionCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc.medical_id";
                joinTable = "join JUNC_SAFETY_MEDICAL_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.safetyCodeList(SimpleMedicalDeviceMalfunctionCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            List list = codeList;
            for(int i = 0; i < codeList.size(); i++)
            {
                SimpleMedicalDeviceMalfunctionCode bean = (SimpleMedicalDeviceMalfunctionCode)list.get(i);
                bean.setNcitDefinitionKor("");
                Integer DepthLevel = Integer.valueOf(bean.getDepthLevel());
                Integer ParentId = Integer.valueOf(bean.getParentId());
                String fullName = "";
                DepthLevel = Integer.valueOf(bean.getDepthLevel());
                ParentId = Integer.valueOf(bean.getParentId());
                if(DepthLevel.intValue() > 1)
                {
                    for(int j = DepthLevel.intValue() - 1; j > 0; j--)
                    {
                        String name = "";
                        parentCode = (SimpleMedicalDeviceMalfunctionCode)propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, ParentId.intValue());
                        ParentId = Integer.valueOf(parentCode.getParentId());
                        DepthLevel = Integer.valueOf(parentCode.getDepthLevel());
                        name = (new StringBuilder("(")).append(parentCode.getDepthLevel()).append(") ").append(parentCode.getFdaSourcePtKor()).append(" :: ").toString();
                        fullName = (new StringBuilder(String.valueOf(name))).append(fullName).toString();
                    }

                }
                bean.setNcitDefinitionKor(fullName);
            }

            mav.addObject("codeList", codeList);
            mav.addObject("level", "0");
            mav.setViewName("/view/jsp/statistics/popMedicalDMC.jsp");
        } else
        if(popupGB.equals("popPatient"))
        {
            orderColName = "fdaSourcePtKor";
            List codeList = null;
            SimplePatientCondition parentCode = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.patient_id";
                joinTable = "join JUNC_SIDE_EFFECT_PATIENT_CONDITION junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectCodeList(SimplePatientCondition.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc.condition_id";
                joinTable = "join JUNC_SAFETY_PATIENT_CONDITION junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.safetyCodeList(SimplePatientCondition.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            List list = codeList;
            for(int i = 0; i < codeList.size(); i++)
            {
                SimplePatientCondition bean = (SimplePatientCondition)list.get(i);
                bean.setNcitDefinitionKor("");
                Integer DepthLevel = bean.getDepthLevel();
                Integer ParentId = bean.getParentId();
                String fullName = "";
                DepthLevel = bean.getDepthLevel();
                ParentId = bean.getParentId();
                if(DepthLevel.intValue() > 1)
                {
                    for(int j = DepthLevel.intValue() - 1; j > 0; j--)
                    {
                        String name = "";
                        parentCode = (SimplePatientCondition)propertiesDAO.read(SimplePatientCondition.class, ParentId.intValue());
                        ParentId = parentCode.getParentId();
                        DepthLevel = parentCode.getDepthLevel();
                        name = (new StringBuilder("(")).append(parentCode.getDepthLevel()).append(") ").append(parentCode.getFdaSourcePtKor()).append(" :: ").toString();
                        fullName = (new StringBuilder(String.valueOf(name))).append(fullName).toString();
                    }

                }
                bean.setNcitDefinitionKor(fullName);
            }

            mav.addObject("codeList", codeList);
            mav.addObject("level", "0");
            mav.setViewName("/view/jsp/statistics/popPatientCondition.jsp");
        } else
        if(popupGB.equals("popComponent"))
        {
            orderColName = "fdaSourcePtKor";
            List codeList = null;
            SimpleComponentCode parentCode = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.component_id";
                joinTable = "join JUNC_SIDE_EFFECT_COMPONENT_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectCodeList(SimpleComponentCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc.component_id";
                joinTable = "join JUNC_SAFETY_COMPONENT_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.safetyCodeList(SimpleComponentCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            List list = codeList;
            for(int i = 0; i < codeList.size(); i++)
            {
                SimpleComponentCode bean = (SimpleComponentCode)list.get(i);
                bean.setNcitDefinitionKor("");
                Integer DepthLevel = bean.getDepthLevel();
                Integer ParentId = bean.getParentId();
                String fullName = "";
                DepthLevel = bean.getDepthLevel();
                ParentId = bean.getParentId();
                if(DepthLevel.intValue() > 1)
                {
                    for(int j = DepthLevel.intValue() - 1; j > 0; j--)
                    {
                        String name = "";
                        parentCode = (SimpleComponentCode)propertiesDAO.read(SimpleComponentCode.class, ParentId.intValue());
                        ParentId = parentCode.getParentId();
                        Integer parentDepthLevel = parentCode.getDepthLevel();
                        name = (new StringBuilder("(")).append(parentCode.getDepthLevel()).append(") ").append(parentCode.getFdaSourcePtKor()).append(" :: ").toString();
                        fullName = (new StringBuilder(String.valueOf(name))).append(fullName).toString();
                    }

                }
                bean.setNcitDefinitionKor(fullName);
            }

            mav.addObject("level", "0");
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popComponentCode.jsp");
        } else
        if(popupGB.equals("popMedicalLevel"))
        {
            orderColName = "fdaSourcePtKor";
            List codeList = null;
            SimpleMedicalDeviceMalfunctionCode parentCode = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.medical_id";
                joinTable = "join JUNC_SIDE_EFFECT_MEDICAL_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectLevelCodeList(SimpleMedicalDeviceMalfunctionCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, Integer.valueOf(Integer.parseInt(level)), reporterTypes);
            } else
            {
                colName = "junc.medical_id";
                joinTable = "join JUNC_SAFETY_MEDICAL_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.safetyLevelCodeList(SimpleMedicalDeviceMalfunctionCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, Integer.valueOf(Integer.parseInt(level)), reporterTypes);
            }
            List list = codeList;
            for(int i = 0; i < codeList.size(); i++)
            {
                SimpleMedicalDeviceMalfunctionCode bean = (SimpleMedicalDeviceMalfunctionCode)list.get(i);
                bean.setNcitDefinitionKor("");
                Integer DepthLevel = Integer.valueOf(bean.getDepthLevel());
                Integer ParentId = Integer.valueOf(bean.getParentId());
                String fullName = "";
                DepthLevel = Integer.valueOf(bean.getDepthLevel());
                ParentId = Integer.valueOf(bean.getParentId());
                if(DepthLevel.intValue() > 1 && DepthLevel.intValue() == Integer.parseInt(level))
                {
                    for(int j = DepthLevel.intValue() - 1; j > 0; j--)
                    {
                        String name = "";
                        parentCode = (SimpleMedicalDeviceMalfunctionCode)propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, ParentId.intValue());
                        ParentId = Integer.valueOf(parentCode.getParentId());
                        DepthLevel = Integer.valueOf(parentCode.getDepthLevel());
                        name = (new StringBuilder("(")).append(parentCode.getDepthLevel()).append(") ").append(parentCode.getFdaSourcePtKor()).append(" :: ").toString();
                        fullName = (new StringBuilder(String.valueOf(name))).append(fullName).toString();
                    }

                }
                bean.setNcitDefinitionKor(fullName);
            }

            mav.addObject("level", level);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popMedicalDMC.jsp");
        } else
        if(popupGB.equals("popPatientLevel"))
        {
            orderColName = "fdaSourcePtKor";
            List codeList = null;
            SimplePatientCondition parentCode = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.patient_id";
                joinTable = "join JUNC_SIDE_EFFECT_PATIENT_CONDITION junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectLevelCodeList(SimplePatientCondition.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, Integer.valueOf(Integer.parseInt(level)), reporterTypes);
            } else
            {
                colName = "junc.condition_id";
                joinTable = "join JUNC_SAFETY_PATIENT_CONDITION junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.safetyLevelCodeList(SimplePatientCondition.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, Integer.valueOf(Integer.parseInt(level)), reporterTypes);
            }
            List list = codeList;
            for(int i = 0; i < codeList.size(); i++)
            {
                SimplePatientCondition bean = (SimplePatientCondition)list.get(i);
                bean.setNcitDefinitionKor("");
                Integer DepthLevel = bean.getDepthLevel();
                Integer ParentId = bean.getParentId();
                String fullName = "";
                DepthLevel = bean.getDepthLevel();
                ParentId = bean.getParentId();
                if(DepthLevel.intValue() > 1)
                {
                    for(int j = DepthLevel.intValue() - 1; j > 0; j--)
                    {
                        String name = "";
                        parentCode = (SimplePatientCondition)propertiesDAO.read(SimplePatientCondition.class, ParentId.intValue());
                        ParentId = parentCode.getParentId();
                        DepthLevel = parentCode.getDepthLevel();
                        name = (new StringBuilder("(")).append(parentCode.getDepthLevel()).append(") ").append(parentCode.getFdaSourcePtKor()).append(" :: ").toString();
                        fullName = (new StringBuilder(String.valueOf(name))).append(fullName).toString();
                    }

                }
                bean.setNcitDefinitionKor(fullName);
            }

            mav.addObject("codeList", codeList);
            mav.addObject("level", level);
            mav.setViewName("/view/jsp/statistics/popPatientCondition.jsp");
        } else
        if(popupGB.equals("popComponentLevel"))
        {
            orderColName = "fdaSourcePtKor";
            List codeList = null;
            SimpleComponentCode parentCode = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.component_id";
                joinTable = "join JUNC_SIDE_EFFECT_COMPONENT_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectLevelCodeList(SimpleComponentCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, Integer.valueOf(Integer.parseInt(level)), reporterTypes);
            } else
            {
                colName = "junc.component_id";
                joinTable = "join JUNC_SAFETY_COMPONENT_CODE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.safetyLevelCodeList(SimpleComponentCode.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, Integer.valueOf(Integer.parseInt(level)), reporterTypes);
            }
            List list = codeList;
            for(int i = 0; i < codeList.size(); i++)
            {
                SimpleComponentCode bean = (SimpleComponentCode)list.get(i);
                bean.setNcitDefinitionKor("");
                Integer DepthLevel = bean.getDepthLevel();
                Integer ParentId = bean.getParentId();
                String fullName = "";
                DepthLevel = bean.getDepthLevel();
                ParentId = bean.getParentId();
                if(DepthLevel.intValue() > 1 && DepthLevel.intValue() == Integer.parseInt(level))
                {
                    for(int j = DepthLevel.intValue() - 1; j > 0; j--)
                    {
                        String name = "";
                        parentCode = (SimpleComponentCode)propertiesDAO.read(SimpleComponentCode.class, ParentId.intValue());
                        ParentId = parentCode.getParentId();
                        Integer parentDepthLevel = parentCode.getDepthLevel();
                        name = (new StringBuilder("(")).append(parentCode.getDepthLevel()).append(") ").append(parentCode.getFdaSourcePtKor()).append(" :: ").toString();
                        fullName = (new StringBuilder(String.valueOf(name))).append(fullName).toString();
                    }

                }
                bean.setNcitDefinitionKor(fullName);
            }

            mav.addObject("level", level);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popComponentCode.jsp");
        } else
        if(popupGB.equals("popMeddevItem"))
        {
            orderColName = "id";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "MEB_ITEM_ID";
                joinTable = " ";
                codeList = propertiesDAO.sideeffectCodeList(SimpleItem1.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "MEB_ITEM_ID";
                joinTable = " ";
                codeList = propertiesDAO.safetyCodeList(SimpleItem1.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popMeddevItem.jsp");
        } else
        if(popupGB.equals("popItemGrade"))
        {
            orderColName = "propertyValue";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc2.GRADE";
                joinTable = "join SGI_SE_ITEM junc on(report.MEB_ITEM_ID = junc.id) join SGI_SE_ITEM_CATEGORY junc2 on (junc.ITEM_CATEGORY_NUMBER = junc2.id)";
                codeList = propertiesDAO.sideeffectCodeList(SimpleItemCategoryGrade.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc2.GRADE";
                joinTable = "join SGI_SE_ITEM junc on(report.MEB_ITEM_ID = junc.id) join SGI_SE_ITEM_CATEGORY junc2 on (junc.ITEM_CATEGORY_NUMBER = junc2.id)";
                codeList = propertiesDAO.safetyCodeList(SimpleItemCategoryGrade.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popItemGrade.jsp");
        } else
        if(popupGB.equals("popCausality"))
        {
            colName = "causality_id";
            joinTable = "";
            orderColName = "propertyValue";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
                codeList = propertiesDAO.sideeffectCodeList(SimpleCausality.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popCausality.jsp");
        } else
        if(popupGB.equals("popSideeffectResult"))
        {
            colName = "junc.result_id";
            joinTable = "join junc_sideeffect_result junc on (report.id = junc.report_id) ";
            orderColName = "propertyValue";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
            {
                codeList = propertiesDAO.sideeffectCodeList(SimpleSideeffectResult.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
                List list = codeList;
                for(int a = 0; a < codeList.size(); a++)
                {
                    String name = "";
                    SimpleSideeffectResult bean = (SimpleSideeffectResult)list.get(a);
                    if(bean.getDepthLevel().intValue() > 1)
                    {
                        name = propertiesDAO.read(SimpleSideeffectResult.class, bean.getParentId()).getPropertyValue();
                        name = (new StringBuilder(String.valueOf(name))).append("(").append(bean.getPropertyValue()).append(")").toString();
                        bean.setPropertyValue(name);
                    }
                }

            }
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popSideeffectResult.jsp");
        } else
        if(popupGB.equals("popCauseOfSideeffect"))
        {
            colName = "junc.cause_id";
            joinTable = "join junc_sideeffect_cause junc on (report.id = junc.report_id) ";
            orderColName = "property_value";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
                codeList = propertiesDAO.sideeffectCodeList(SimpleSideeffectCause.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popSideeffectCause.jsp");
        } else
        if(popupGB.equals("popReporterType"))
        {
            orderColName = "propertyValue";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.REPORT_TYPE_ID";
                joinTable = "join JUNC_SIDEEFFECT_REPORT_TYPE junc on (report.id = junc.report_id) ";
                codeList = propertiesDAO.sideeffectCodeList(SimpleReporterType.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc.reporter_type";
                joinTable = "join junc_safety_reporter_types junc on (report.id = junc.safety_report) ";
                codeList = propertiesDAO.safetyCodeList(SimpleReporterType.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popReporterType.jsp");
        } else
        if(popupGB.equals("popMeaCompany"))
        {
            orderColName = "entp_name";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
            {
                colName = "junc.company_id";
                joinTable = "join SGI_SE_ITEM junc on (report.MEB_ITEM_ID = junc.id) ";
                codeList = itemCategoryDAO.sideeffectMeaCompany(colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            } else
            {
                colName = "junc.company_id";
                joinTable = "join SGI_SE_ITEM junc on (report.MEB_ITEM_ID = junc.id) ";
                codeList = itemCategoryDAO.safetyMeaCompany(colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            }
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popCompany.jsp");
        } else
        if(popupGB.equals("popCountry"))
        {
            orderColName = "propertyValue";
            colName = "COUNTRY_MANUFACTURED_ID";
            joinTable = "";
            List codeList = null;
            if(selectGB.equals("sideeffect"))
                codeList = propertiesDAO.sideeffectCodeList(SimpleCountry.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            else
                codeList = propertiesDAO.safetyCodeList(SimpleCountry.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popCountry.jsp");
        } else
        if(popupGB.equals("popSideEffectReportType"))
        {
            orderColName = "propertyValue";
            List codeList = null;
            colName = "junc.report_type_id";
            joinTable = "join JUNC_SAFETY_REPORT_TYPE junc on (report.id = junc.report_id) ";
            codeList = propertiesDAO.safetyCodeList(SimpleSideEffectReportType.class, colName, joinTable, reportType, fmYY, fmMM, toYY, toMM, orderColName, reporterTypes);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/popSideEffectReportType.jsp");
        }
        mav.addObject("dataSet", "ds_codeList");
        setDefaultViewSet(mav);
        return mav;
    }

    public ModelAndView selectSideEffect(HttpServletRequest request, HttpServletResponse response)
        throws PlatformException
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String popName = in_vl.getString("varCodeName");
        String fmYY = in_vl.getString("varfmYY");
        String fmMM = in_vl.getString("varfmMM");
        String toYY = in_vl.getString("vartoYY");
        String toMM = in_vl.getString("vartoMM");
        String fmDate = (new StringBuilder(String.valueOf(fmYY))).append(fmMM).toString();
        String toDate = (new StringBuilder(String.valueOf(toYY))).append(toMM).toString();
        String reportType = in_vl.getString("varReportType");
        ArrayList getIdArr = new ArrayList();
        ArrayList getNameArr = new ArrayList();
        ArrayList getDepthLevelArr = new ArrayList();
        String reporterTypes = "";
        DataSet ds_reporterTypes = i_xpData.getDataSet("ds_reporterTypes");
        try
        {
            Integer ds_chk = Integer.valueOf(0);
            for(int i = 0; i < ds_reporterTypes.getRowCount(); i++)
            {
                String check_yn = dsGet(ds_reporterTypes, i, "CHECK_YN");
                if(check_yn.equals("1"))
                {
                    ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
                    reporterTypes = (new StringBuilder(String.valueOf(reporterTypes))).append(dsGet(ds_reporterTypes, i, "ID")).append(",").toString();
                }
            }

            if(ds_chk.intValue() > 0)
                reporterTypes = reporterTypes.substring(0, reporterTypes.length() - 1);
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        DataSet ds = i_xpData.getDataSet("ds_codeList");
        try
        {
            for(int i = 0; i < ds.getRowCount(); i++)
            {
                int rowType = ds.getRowType(i);
                String chk = dsGet(ds, i, "CHECK_YN");
                if(chk.equals("1"))
                {
                    String getId = dsGet(ds, i, "ID");
                    String getName = dsGet(ds, i, "NAME");
                    String getDepthLevel = dsGet(ds, i, "DEPTH_LEVEL");
                    getIdArr.add(getId);
                    getNameArr.add(getName);
                    getDepthLevelArr.add(getDepthLevel);
                }
            }

        }
        catch(Throwable th)
        {
            nErrorCode = -1;
            strErrorMsg = th.getMessage();
        }
        DataSet ds_child = i_xpData.getDataSet("ds_childCode");
        int getParentChildArr[][] = new int[ds_child.getRowCount()][2];
        try
        {
            for(int i = 0; i < ds_child.getRowCount(); i++)
            {
                String getParentId = dsGet(ds_child, i, "PARENT_ID");
                String getChildId = dsGet(ds_child, i, "CHILD_ID");
                getParentChildArr[i][0] = Integer.parseInt(getParentId);
                getParentChildArr[i][1] = Integer.parseInt(getChildId);
            }

        }
        catch(Throwable th)
        {
            nErrorCode = -1;
            strErrorMsg = th.getMessage();
        }
        ArrayList codeArr = new ArrayList();
        ArrayList cntArr = new ArrayList();
        ArrayList dateArr = new ArrayList();
        ArrayList nameArr = new ArrayList();
        ArrayList depthLevelArr = new ArrayList();
        String TabName = "";
        String JoinYn = "";
        String JoinColName = "";
        String Type = "";
        String ColName = "id";
        String TreeYN = "N";
        if(getParentChildArr.length > 0)
            TreeYN = "Y";
        if(popName.equals("popReporterType"))
        {
            JoinYn = "Y";
            TabName = "junc_sideeffect_report_type a";
            JoinColName = "a.report_id = b.id";
            ColName = "a.report_type_id";
        } else
        if(popName.equals("popMeaCompany"))
        {
            JoinYn = "Y";
            TabName = "sgi_se_item a";
            JoinColName = "a.id = b.meb_item_id ";
            ColName = "a.company_id";
        } else
        if(popName.equals("popCountry"))
        {
            JoinYn = "N";
            ColName = "country_manufactured_id";
        } else
        if(popName.equals("popMeaClassNo"))
        {
            JoinYn = "Y";
            TabName = "sgi_se_item a";
            JoinColName = "a.id = b.meb_item_id ";
            ColName = "a.item_category_number";
        } else
        if(popName.equals("popItemGrade"))
        {
            JoinYn = "Y";
            TabName = "sgi_se_item_category d join sgi_se_item a on (d.id = a.item_category_number)";
            JoinColName = "a.id = b.meb_item_id ";
            ColName = "d.grade";
        } else
        if(popName.equals("popCausality"))
        {
            JoinYn = "N";
            ColName = "causality_id";
        } else
        if(popName.equals("popPatient"))
        {
            JoinYn = "Y";
            TabName = "junc_side_effect_patient_condition a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.patient_id";
        } else
        if(popName.equals("popMedical"))
        {
            JoinYn = "Y";
            TabName = "junc_side_effect_medical_code a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.medical_id";
        } else
        if(popName.equals("popComponent"))
        {
            JoinYn = "Y";
            TabName = "junc_side_effect_component_code a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.component_id";
        } else
        if(popName.equals("popPatientLevel"))
        {
            JoinYn = "Y";
            TabName = "junc_side_effect_patient_condition a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.patient_id";
            TreeYN = "Y";
        } else
        if(popName.equals("popMedicalLevel"))
        {
            JoinYn = "Y";
            TabName = "junc_side_effect_medical_code a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.medical_id";
            TreeYN = "Y";
        } else
        if(popName.equals("popComponentLevel"))
        {
            JoinYn = "Y";
            TabName = "junc_side_effect_component_code a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.component_id";
            TreeYN = "Y";
        } else
        if(popName.equals("popCauseOfSideeffect"))
        {
            JoinYn = "Y";
            TabName = "junc_sideeffect_cause a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.cause_id";
        } else
        if(popName.equals("popSideeffectResult"))
        {
            JoinYn = "Y";
            TabName = "junc_sideeffect_result a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.result_id";
        } else
        if(popName.equals("popMeddevItem"))
        {
            JoinYn = "N";
            TabName = "";
            JoinColName = "";
            ColName = "meb_item_id";
        }
        for(int a = 0; a < getIdArr.size(); a++)
        {
            String code = "";
            String code_bk = "";
            if(Type.equals("S"))
                code = (new StringBuilder("'")).append((String)getIdArr.get(a)).append("'").toString();
            else
                code = (String)getIdArr.get(a);
            code_bk = code;
            List selectList = null;
            if(JoinYn.equals("N"))
            {
                if(reportType.equals("0"))
                    selectList = sideEffectDAO.sideeffectCnt(ColName, Integer.valueOf(Integer.parseInt(code)), fmDate, toDate, reporterTypes);
                else
                    selectList = sideEffectDAO.sideeffectCnt(ColName, Integer.valueOf(Integer.parseInt(code)), fmDate, toDate, reportType, reporterTypes);
            } else
            {
                if(TreeYN.equals("Y"))
                {
                    String childArr = "";
                    for(int n = 0; n < getParentChildArr.length; n++)
                    {
                        Integer parentId = Integer.valueOf(getParentChildArr[n][0]);
                        if(parentId.equals(Integer.valueOf(Integer.parseInt(code))))
                            childArr = (new StringBuilder(String.valueOf(childArr))).append(getParentChildArr[n][1]).append(",").toString();
                    }

                    childArr = childArr.substring(0, childArr.length() - 1);
                    code = childArr;
                }
                if(reportType.equals("0"))
                    selectList = sideEffectDAO.sideeffectCnt(TabName, JoinColName, ColName, code, fmDate, toDate, TreeYN, reporterTypes);
                else
                    selectList = sideEffectDAO.sideeffectCnt(TabName, JoinColName, ColName, code, fmDate, toDate, reportType, TreeYN, reporterTypes);
                code = code_bk;
            }
            if(!selectList.equals(null))
            {
                for(int b = 0; b < selectList.size(); b++)
                {
                    cntArr.add(((SimpleSideeffectReport)selectList.get(b)).getVarCnt().toString());
                    dateArr.add((new StringBuilder(String.valueOf(((SimpleSideeffectReport)selectList.get(b)).getVarYear()))).append(((SimpleSideeffectReport)selectList.get(b)).getVarMonth()).toString());
                    codeArr.add(code);
                    nameArr.add((String)getNameArr.get(a));
                    depthLevelArr.add((String)getDepthLevelArr.get(a));
                }

            }
        }

        mav.addObject("codeArr", codeArr);
        mav.addObject("nameArr", nameArr);
        mav.addObject("cntArr", cntArr);
        mav.addObject("dateArr", dateArr);
        mav.addObject("depthLevelArr", depthLevelArr);
        mav.setViewName("/view/jsp/statistics/codeStatistics.jsp");
        return mav;
    }

    public ModelAndView selectSafety(HttpServletRequest request, HttpServletResponse response)
        throws PlatformException
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String popName = in_vl.getString("varCodeName");
        String fmYY = in_vl.getString("varfmYY");
        String fmMM = in_vl.getString("varfmMM");
        String toYY = in_vl.getString("vartoYY");
        String toMM = in_vl.getString("vartoMM");
        String fmDate = (new StringBuilder(String.valueOf(fmYY))).append(fmMM).toString();
        String toDate = (new StringBuilder(String.valueOf(toYY))).append(toMM).toString();
        String reportType = in_vl.getString("varReportType");
        ArrayList getIdArr = new ArrayList();
        ArrayList getNameArr = new ArrayList();
        ArrayList getDepthLevelArr = new ArrayList();
        String reporterTypes = "";
        DataSet ds_reporterTypes = i_xpData.getDataSet("ds_reporterTypes");
        try
        {
            Integer ds_chk = Integer.valueOf(0);
            for(int i = 0; i < ds_reporterTypes.getRowCount(); i++)
            {
                String check_yn = dsGet(ds_reporterTypes, i, "CHECK_YN");
                if(check_yn.equals("1"))
                {
                    ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
                    reporterTypes = (new StringBuilder(String.valueOf(reporterTypes))).append(dsGet(ds_reporterTypes, i, "ID")).append(",").toString();
                }
            }

            if(ds_chk.intValue() > 0)
                reporterTypes = reporterTypes.substring(0, reporterTypes.length() - 1);
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        DataSet ds = i_xpData.getDataSet("ds_codeList");
        try
        {
            for(int i = 0; i < ds.getRowCount(); i++)
            {
                int rowType = ds.getRowType(i);
                String chk = dsGet(ds, i, "CHECK_YN");
                if(chk.equals("1"))
                {
                    String getId = dsGet(ds, i, "ID");
                    String getName = dsGet(ds, i, "NAME");
                    String getDepthLevel = dsGet(ds, i, "DEPTH_LEVEL");
                    getIdArr.add(getId);
                    getNameArr.add(getName);
                    getDepthLevelArr.add(getDepthLevel);
                }
            }

        }
        catch(Throwable th)
        {
            nErrorCode = -1;
            strErrorMsg = th.getMessage();
        }
        DataSet ds_child = i_xpData.getDataSet("ds_childCode");
        int getParentChildArr[][] = new int[ds_child.getRowCount()][2];
        try
        {
            for(int i = 0; i < ds_child.getRowCount(); i++)
            {
                String getParentId = dsGet(ds_child, i, "PARENT_ID");
                String getChildId = dsGet(ds_child, i, "CHILD_ID");
                getParentChildArr[i][0] = Integer.parseInt(getParentId);
                getParentChildArr[i][1] = Integer.parseInt(getChildId);
            }

        }
        catch(Throwable th)
        {
            nErrorCode = -1;
            strErrorMsg = th.getMessage();
        }
        ArrayList codeArr = new ArrayList();
        ArrayList cntArr = new ArrayList();
        ArrayList dateArr = new ArrayList();
        ArrayList nameArr = new ArrayList();
        ArrayList depthLevelArr = new ArrayList();
        String TabName = "";
        String JoinYn = "";
        String JoinColName = "";
        String Type = "";
        String ColName = "id";
        String TreeYN = "N";
        if(getParentChildArr.length > 0)
            TreeYN = "Y";
        if(popName.equals("popReporterType"))
        {
            JoinYn = "Y";
            TabName = "junc_safety_reporter_types a";
            JoinColName = "a.safety_report = b.id";
            ColName = "a.reporter_type";
        } else
        if(popName.equals("popMeaCompany"))
        {
            JoinYn = "Y";
            TabName = "sgi_se_item a";
            JoinColName = "a.id = b.meb_item_id ";
            ColName = "a.company_id";
        } else
        if(popName.equals("popCountry"))
        {
            JoinYn = "N";
            ColName = "country_manufactured_id";
        } else
        if(popName.equals("popMeaClassNo"))
        {
            JoinYn = "Y";
            TabName = "sgi_se_item a";
            JoinColName = "a.id = b.meb_item_id ";
            ColName = "a.item_category_number";
        } else
        if(popName.equals("popItemGrade"))
        {
            JoinYn = "Y";
            TabName = "sgi_se_item_category d join sgi_se_item a on (d.id = a.item_category_number)";
            JoinColName = "a.id = b.meb_item_id ";
            ColName = "d.grade";
        } else
        if(popName.equals("popPatient"))
        {
            JoinYn = "Y";
            TabName = "junc_safety_patient_condition a";
            JoinColName = "a.report_id= b.id ";
            ColName = "condition_id";
        } else
        if(popName.equals("popMedical"))
        {
            JoinYn = "Y";
            TabName = "junc_safety_medical_code a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.medical_id";
        } else
        if(popName.equals("popComponent"))
        {
            JoinYn = "Y";
            TabName = "junc_safety_component_code a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.component_id";
        } else
        if(popName.equals("popSideEffectReportType"))
        {
            JoinYn = "Y";
            TabName = "junc_safety_report_type a";
            JoinColName = "a.report_id= b.id ";
            ColName = "a.report_type_id";
        } else
        if(popName.equals("popMeddevItem"))
        {
            JoinYn = "N";
            TabName = "";
            JoinColName = "";
            ColName = "meb_item_id";
        }
        for(int a = 0; a < getIdArr.size(); a++)
        {
            String code = "";
            String code_bk = "";
            if(Type.equals("S"))
                code = (new StringBuilder("'")).append((String)getIdArr.get(a)).append("'").toString();
            else
                code = (String)getIdArr.get(a);
            code_bk = code;
            if(TreeYN.equals("Y"))
            {
                String childArr = "";
                for(int n = 0; n < getParentChildArr.length; n++)
                {
                    Integer parentId = Integer.valueOf(getParentChildArr[n][0]);
                    if(parentId.equals(Integer.valueOf(Integer.parseInt(code))))
                        childArr = (new StringBuilder(String.valueOf(childArr))).append(getParentChildArr[n][1]).append(",").toString();
                }

                childArr = childArr.substring(0, childArr.length() - 1);
                code = childArr;
            }
            List selectList = null;
            if(JoinYn.equals("N"))
            {
                if(reportType.equals("0"))
                    selectList = safetyDAO.safetyCnt(ColName, Integer.valueOf(Integer.parseInt(code)), fmDate, toDate, reporterTypes);
                else
                    selectList = safetyDAO.safetyCnt(ColName, Integer.valueOf(Integer.parseInt(code)), fmDate, toDate, reportType, reporterTypes);
            } else
            if(reportType.equals("0"))
                selectList = safetyDAO.safetyCnt(TabName, JoinColName, ColName, code, fmDate, toDate, reporterTypes);
            else
                selectList = safetyDAO.safetyCnt(TabName, JoinColName, ColName, code, fmDate, toDate, reportType, reporterTypes);
            code = code_bk;
            if(!selectList.equals(null))
            {
                for(int b = 0; b < selectList.size(); b++)
                {
                    cntArr.add(((SimpleSafetyReport)selectList.get(b)).getVarCnt().toString());
                    dateArr.add((new StringBuilder(String.valueOf(((SimpleSafetyReport)selectList.get(b)).getVarYear()))).append(((SimpleSafetyReport)selectList.get(b)).getVarMonth()).toString());
                    codeArr.add(code);
                    nameArr.add((String)getNameArr.get(a));
                    depthLevelArr.add((String)getDepthLevelArr.get(a));
                }

            }
        }

        mav.addObject("codeArr", codeArr);
        mav.addObject("nameArr", nameArr);
        mav.addObject("cntArr", cntArr);
        mav.addObject("dateArr", dateArr);
        mav.addObject("depthLevelArr", depthLevelArr);
        mav.setViewName("/view/jsp/statistics/codeStatistics.jsp");
        return mav;
    }

    public String dsGet(DataSet ds, int rowno, String colid)
        throws Exception
    {
        String value = ds.getString(rowno, colid);
        if(value == null)
            return "";
        else
            return value;
    }

    public ModelAndView popSideeffectM(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        String popName = Function.nullChk(request.getParameter("popName"), "");
        String type = "M";
        mav = new ModelAndView();
        mav.addObject("popName", popName);
        mav.addObject("type", type);
        mav.setViewName("/view/jsp/statistics/popSideeffect.jsp");
        return mav;
    }

    public ModelAndView popSideeffectY(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        String popName = Function.nullChk(request.getParameter("popName"), "");
        String type = "Y";
        mav = new ModelAndView();
        mav.addObject("popName", popName);
        mav.addObject("type", type);
        mav.setViewName("/view/jsp/statistics/popSideeffect.jsp");
        return mav;
    }

    public ModelAndView popSafetyM(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        String popName = Function.nullChk(request.getParameter("popName"), "");
        String type = "M";
        mav = new ModelAndView();
        mav.addObject("popName", popName);
        mav.addObject("type", type);
        mav.setViewName("/view/jsp/statistics/popSafety.jsp");
        return mav;
    }

    public ModelAndView popSafetyY(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        String popName = Function.nullChk(request.getParameter("popName"), "");
        String type = "Y";
        mav = new ModelAndView();
        mav.addObject("popName", popName);
        mav.addObject("type", type);
        mav.setViewName("/view/jsp/statistics/popSafety.jsp");
        return mav;
    }

    public ModelAndView chart(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = null;
        mav = new ModelAndView();
        mav.setViewName("/view/jsp/statistics/chart.jsp");
        return mav;
    }

    public ModelAndView sideEffectStatistics(HttpServletRequest request, HttpServletResponse respnse)
    {
        System.out.println("[SimpleStatisticsDelegate].sideEffectStatistics()");
        ModelAndView mav = new ModelAndView();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        Calendar cal = Calendar.getInstance();
        java.util.Date year_minus = null;
        cal.add(1, 0);
        year_minus = cal.getTime();
        String year_1 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_2 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_3 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_4 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_5 = dateFormat.format(year_minus);
        Object sicount_5 = propertiesDAO.sideEffectMreportCount(year_5.toString());
        Object sicount_4 = propertiesDAO.sideEffectMreportCount(year_4.toString());
        Object sicount_3 = propertiesDAO.sideEffectMreportCount(year_3.toString());
        Object sicount_2 = propertiesDAO.sideEffectMreportCount(year_2.toString());
        Object sicount_1 = propertiesDAO.sideEffectMreportCount(year_1.toString());
        mav.addObject("sicount_5", sicount_5);
        mav.addObject("sicount_4", sicount_4);
        mav.addObject("sicount_3", sicount_3);
        mav.addObject("sicount_2", sicount_2);
        mav.addObject("sicount_1", sicount_1);
        Object sacount_5 = propertiesDAO.safetyMreportCount(year_5.toString());
        Object sacount_4 = propertiesDAO.safetyMreportCount(year_4.toString());
        Object sacount_3 = propertiesDAO.safetyMreportCount(year_3.toString());
        Object sacount_2 = propertiesDAO.safetyMreportCount(year_2.toString());
        Object sacount_1 = propertiesDAO.safetyMreportCount(year_1.toString());
        mav.addObject("sacount_5", sacount_5);
        mav.addObject("sacount_4", sacount_4);
        mav.addObject("sacount_3", sacount_3);
        mav.addObject("sacount_2", sacount_2);
        mav.addObject("sacount_1", sacount_1);
        mav.addObject("year_5", year_5.toString());
        mav.addObject("year_4", year_4.toString());
        mav.addObject("year_3", year_3.toString());
        mav.addObject("year_2", year_2.toString());
        mav.addObject("year_1", year_1.toString());
        setDefaultViewSet(mav, request);
        mav.addObject("leftName", "/view/jsp/statistics/statisticsLeft.jsp");
        mav.addObject("contentName", "/view/jsp/statistics/sideEffectStatistics.jsp");
        mav.addObject("titleImg", "view/style/images/title/sub02_01.jpg");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img2.jpg");
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        return mav;
    }

    public ModelAndView xpSideEffectStatisticsAndReport(HttpServletRequest request, HttpServletResponse respnse)
    {
        System.out.println("[SimpleStatisticsDelegate].xpStatisticsAndReport()");
        ModelAndView mav = new ModelAndView();
        String popName = Function.nullChk(request.getParameter("popName"), "");
        String type = "M";
        mav.addObject("popName", popName);
        mav.addObject("type", type);
        setDefaultViewSet(mav, request);
        mav.addObject("leftName", "/view/jsp/statistics/statisticsLeft.jsp");
        mav.addObject("contentName", "/view/jsp/statistics/sideEffectStatisticsAndReportXpLink.jsp");
        mav.addObject("titleImg", "view/style/images/title/sub02_01.jpg");
        mav.addObject("titleImage", "");
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        return mav;
    }

    public ModelAndView safetyStatistics(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        Calendar cal = Calendar.getInstance();
        java.util.Date year_minus = null;
        cal.add(1, 0);
        year_minus = cal.getTime();
        String year_1 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_2 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_3 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_4 = dateFormat.format(year_minus);
        cal.add(1, -1);
        year_minus = cal.getTime();
        String year_5 = dateFormat.format(year_minus);
        Object sicount_5 = propertiesDAO.sideEffectMreportCount(year_5.toString());
        Object sicount_4 = propertiesDAO.sideEffectMreportCount(year_4.toString());
        Object sicount_3 = propertiesDAO.sideEffectMreportCount(year_3.toString());
        Object sicount_2 = propertiesDAO.sideEffectMreportCount(year_2.toString());
        Object sicount_1 = propertiesDAO.sideEffectMreportCount(year_1.toString());
        mav.addObject("sicount_5", sicount_5);
        mav.addObject("sicount_4", sicount_4);
        mav.addObject("sicount_3", sicount_3);
        mav.addObject("sicount_2", sicount_2);
        mav.addObject("sicount_1", sicount_1);
        Object sacount_5 = propertiesDAO.safetyMreportCount(year_5.toString());
        Object sacount_4 = propertiesDAO.safetyMreportCount(year_4.toString());
        Object sacount_3 = propertiesDAO.safetyMreportCount(year_3.toString());
        Object sacount_2 = propertiesDAO.safetyMreportCount(year_2.toString());
        Object sacount_1 = propertiesDAO.safetyMreportCount(year_1.toString());
        mav.addObject("sacount_5", sacount_5);
        mav.addObject("sacount_4", sacount_4);
        mav.addObject("sacount_3", sacount_3);
        mav.addObject("sacount_2", sacount_2);
        mav.addObject("sacount_1", sacount_1);
        mav.addObject("year_5", year_5.toString());
        mav.addObject("year_4", year_4.toString());
        mav.addObject("year_3", year_3.toString());
        mav.addObject("year_2", year_2.toString());
        mav.addObject("year_1", year_1.toString());
        setDefaultViewSet(mav, request);
        mav.addObject("contentName", "/view/jsp/statistics/safetyStatistics.jsp");
        mav.addObject("titleImg", "view/style/images/title/sub02_02.jpg");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img2.jpg");
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        return mav;
    }

    public ModelAndView xpSafetyStatisticsAndReport(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        String popName = Function.nullChk(request.getParameter("popName"), "");
        String type = "M";
        mav.addObject("popName", popName);
        mav.addObject("type", type);
        setDefaultViewSet(mav, request);
        mav.addObject("contentName", "/view/jsp/statistics/safetyStatisticsAndReportXpLink.jsp");
        mav.addObject("titleImg", "view/style/images/title/sub02_02.jpg");
        mav.addObject("titleImage", "");
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        return mav;
    }

    public ModelAndView reportTypeList(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        List reportTypes = propertiesDAO.list(SimpleReportType2.class);
        mav.addObject("reportTypes", reportTypes);
        mav.setViewName("/view/jsp/statistics/reportTypes.jsp");
        return mav;
    }

    public ModelAndView xpSideEffectItemStatistics(HttpServletRequest request, HttpServletResponse respnse)
    {
        System.out.println("[SimpleStatisticsDelegate].xpStatisticsAndReport()");
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        mav.addObject("statisticsGB", "sideeffect");
        mav.addObject("contentName", "/view/jsp/statistics/xp_sideEffectItemStatistics.jsp");
        mav.addObject("titleImg", "view/style/images/title/sub02_01.jpg");
        mav.addObject("titleImage", "");
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        return mav;
    }

    public ModelAndView xpSafetyItemStatistics(HttpServletRequest request, HttpServletResponse respnse)
    {
        System.out.println("[SimpleStatisticsDelegate].xpStatisticsAndReport()");
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        mav.addObject("statisticsGB", "safety");
        mav.addObject("contentName", "/view/jsp/statistics/xp_sideEffectItemStatistics.jsp");
        mav.addObject("titleImg", "view/style/images/title/sub02_02.jpg");
        mav.addObject("titleImage", "");
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        return mav;
    }

    public ModelAndView productCodeList(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        String search = Function.nullChk(request.getParameter("search"), "");
        search = (new StringBuilder("%")).append(search).append("%").toString();
        List codeList = propertiesDAO.codeList(SimpleItemCodeType1.class, "propertyValue", search);
        mav.addObject("codeList", codeList);
        mav.setViewName("/view/jsp/statistics/productCodeList.jsp");
        return mav;
    }

    public ModelAndView itemStatisticsList(HttpServletRequest request, HttpServletResponse respnse)
        throws PlatformException
    {
        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String classKorName = in_vl.getString("classKorName");
        String meaClassNo = in_vl.getString("meaClassNo");
        String cobFlag = in_vl.getString("cobFlag");
        String meddevItemNo = in_vl.getString("meddevItemNo");
        String reportType = in_vl.getString("reportType");
        String fmYY = in_vl.getString("fmYY");
        String fmMM = in_vl.getString("fmMM");
        String toYY = in_vl.getString("toYY");
        String toMM = in_vl.getString("toMM");
        int pgSize = in_vl.getInt("pgSize");
        int startPg = in_vl.getInt("startPg");
        String moreYN = in_vl.getString("moreYN");
        String statisticsGB = in_vl.getString("statisticsGB");
        String classKorNameEqual = in_vl.getString("classKorNameEqual");
        long totalCnt = 0L;
        if(statisticsGB.equals("sideeffect"))
        {
            if(moreYN.equals("N"))
            {
                totalCnt = sideEffectDAO.ItemSideeffectCnt(classKorName, meaClassNo, cobFlag, meddevItemNo, reportType, fmYY, fmMM, toYY, toMM, classKorNameEqual);
                startPg = 0;
            }
            List ItemSideeffectList = sideEffectDAO.ItemSideeffectList(classKorName, meaClassNo, cobFlag, meddevItemNo, reportType, fmYY, fmMM, toYY, toMM, pgSize, startPg, classKorNameEqual);
            mav.addObject("ItemSideeffectList", ItemSideeffectList);
            mav.addObject("ItemSideeffectCnt", Long.valueOf(totalCnt));
            mav.setViewName("/view/jsp/statistics/sideEffectItemStatistics.jsp");
        } else
        {
            if(moreYN.equals("N"))
            {
                totalCnt = safetyDAO.ItemSafetyCnt(classKorName, meaClassNo, cobFlag, meddevItemNo, reportType, fmYY, fmMM, toYY, toMM, classKorNameEqual);
                startPg = 0;
            }
            List ItemSafetyList = safetyDAO.ItemSafetyList(classKorName, meaClassNo, cobFlag, meddevItemNo, reportType, fmYY, fmMM, toYY, toMM, pgSize, startPg, classKorNameEqual);
            mav.addObject("ItemSafetyList", ItemSafetyList);
            mav.addObject("ItemSafetyCnt", Long.valueOf(totalCnt));
            mav.setViewName("/view/jsp/statistics/safetyItemStatistics.jsp");
        }
        mav.addObject("moreYN", moreYN);
        return mav;
    }

    public ModelAndView itemStatisticsView(HttpServletRequest request, HttpServletResponse respnse)
        throws PlatformException
    {
        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String itemSeq = Function.nullChk(in_vl.getString("itemSeq"), "0");
        String codeGB = in_vl.getString("codeGB");
        String reportType = in_vl.getString("reportType");
        String fmYY = in_vl.getString("fmYY");
        String fmMM = in_vl.getString("fmMM");
        String toYY = in_vl.getString("toYY");
        String toMM = in_vl.getString("toMM");
        String tabGB = in_vl.getString("tabGB");
        String joinTable = "";
        String statisticsGB = in_vl.getString("statisticsGB");
        if(codeGB.equals("P"))
            joinTable = "patientCodeCondition";
        else
        if(codeGB.equals("M"))
            joinTable = "medicalCode";
        else
        if(codeGB.equals("C"))
            joinTable = "juncComponentCode";
        long itemId = Long.parseLong(itemSeq);
        SimpleItem1 simpleitem1 = new SimpleItem1();
        SimpleItemCategory itemCategory = new SimpleItemCategory();
        if(tabGB.equals("I"))
            simpleitem1 = (SimpleItem1)itemDAO.read(SimpleItem1.class, itemId);
        else
            itemCategory = (SimpleItemCategory)itemCategoryDAO.read(SimpleItemCategory.class, itemId);
        if(statisticsGB.equals("sideeffect"))
        {
            List codeList = sideEffectDAO.ItemSideeffectCodeList(itemId, joinTable, reportType, fmYY, fmMM, toYY, toMM, tabGB);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/sideEffectItemViwStatistics.jsp");
        } else
        {
            List codeList = safetyDAO.ItemSafetyCodeList(itemId, joinTable, reportType, fmYY, fmMM, toYY, toMM, tabGB);
            mav.addObject("codeList", codeList);
            mav.setViewName("/view/jsp/statistics/safetyItemViewStatistics.jsp");
        }
        mav.addObject("itemCategory", itemCategory);
        mav.addObject("simpleitem1", simpleitem1);
        mav.addObject("tabGB", tabGB);
        return mav;
    }

    public ModelAndView itemFrequency(HttpServletRequest request, HttpServletResponse respnse)
        throws PlatformException
    {
        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String itemSeq = Function.nullChk(in_vl.getString("itemSeq"), "0");
        String codeGB = in_vl.getString("codeGB");
        String reportType = in_vl.getString("reportType");
        String fmDate = in_vl.getString("fmDate");
        String toDate = in_vl.getString("toDate");
        String tabGB = in_vl.getString("tabGB");
        String categorySeq = in_vl.getString("categorySeq");
        String codeArr = in_vl.getString("codeArr");
        String whereCol = "";
        String statisticsGB = in_vl.getString("statisticsGB");
        System.out.println((new StringBuilder("codeGB=======")).append(codeGB).toString());
        if(codeGB.equals("popPatient"))
        {
            codeGB = "P";
            whereCol = " and pat.patient_id";
        } else
        if(codeGB.equals("popMedical"))
        {
            codeGB = "M";
            whereCol = " and med.medical_id";
        } else
        if(codeGB.equals("popComponent"))
        {
            codeGB = "C";
            whereCol = " and com.component_id";
        }
        System.out.println((new StringBuilder("categorySeq=======")).append(categorySeq).toString());
        System.out.println((new StringBuilder("itemSeq=======")).append(itemSeq).toString());
        long itemId = Long.parseLong(itemSeq);
        SimpleItem1 simpleitem1 = new SimpleItem1();
        SimpleItemCategory itemCategory = new SimpleItemCategory();
        if(tabGB.equals("I") && statisticsGB.equals("sideeffect"))
        {
            simpleitem1 = (SimpleItem1)itemDAO.read(SimpleItem1.class, itemId);
            SimpleSideeffectReport tergetSideeffect = new SimpleSideeffectReport();
            SimpleSideeffectReport allTergetSideeffect = new SimpleSideeffectReport();
            tergetSideeffect = sideEffectDAO.itemFrequency(fmDate, toDate, reportType, "EQ", whereCol, codeGB, Long.parseLong(itemSeq), Integer.valueOf(Integer.parseInt(categorySeq)), Long.parseLong(codeArr));
            allTergetSideeffect = sideEffectDAO.itemFrequency(fmDate, toDate, reportType, "NE", whereCol, codeGB, Long.parseLong(itemSeq), Integer.valueOf(Integer.parseInt(categorySeq)), Long.parseLong(codeArr));
            mav.addObject("tabGB", tabGB);
            mav.addObject("simpleitem1", simpleitem1);
            mav.addObject("tergetSideeffect", tergetSideeffect);
            mav.addObject("allTergetSideeffect", allTergetSideeffect);
            mav.setViewName("/view/jsp/statistics/sideEffectItemFrequency.jsp");
        }
        return mav;
    }

    public ModelAndView selectSideEffectSearch(HttpServletRequest request, HttpServletResponse respnse)
        throws PlatformException
    {
        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String fmDate = in_vl.getString("varfmDate");
        String toDate = in_vl.getString("vartoDate");
        String reportType = in_vl.getString("varReportType");
        String step = in_vl.getString("curStep");
        Integer curStep = Integer.valueOf(Integer.parseInt(Function.nullChk(step, "0")));
        DataSet de_select_code = i_xpData.getDataSet("de_select_code");
        String stepCodeArr[][] = new String[curStep.intValue() + 1][6];
        String reporterType = "";
        try
        {
            for(int a = 0; a < curStep.intValue(); a++)
            {
                String popName = dsGet(de_select_code, a, "STEP_CODE");
                String stepID = dsGet(de_select_code, a, "STEP_ID");
                String JoinYn = "";
                String TabName = "";
                String ColName = "";
                String stepIDName = "";
                String dataType = "L";
                String reporterTypeYN = "N";
                if(popName.equals("popReporterType"))
                {
                    JoinYn = "Y";
                    TabName = "reporterTypes";
                    ColName = "id";
                    stepIDName = "propertyValue";
                    reporterTypeYN = "Y";
                } else
                if(popName.equals("popMeaCompany"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item company";
                    ColName = "id";
                    stepIDName = "entp_name";
                } else
                if(popName.equals("popCountry"))
                {
                    JoinYn = "Y";
                    TabName = "country_manufactured";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popMeaClassNo"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item mea_item";
                    ColName = "id";
                    stepIDName = "class_kor_name";
                } else
                if(popName.equals("popItemGrade"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item mea_item categoryGrade";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popCausality"))
                {
                    JoinYn = "Y";
                    TabName = "causality";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popPatient"))
                {
                    JoinYn = "Y";
                    TabName = "patientCodeCondition";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popMedical"))
                {
                    JoinYn = "Y";
                    TabName = "medicalCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popComponent"))
                {
                    JoinYn = "Y";
                    TabName = "juncComponentCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popPatientLevel"))
                {
                    JoinYn = "Y";
                    TabName = "patientCodeCondition";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popMedicalLevel"))
                {
                    JoinYn = "Y";
                    TabName = "medicalCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popComponentLevel"))
                {
                    JoinYn = "Y";
                    TabName = "juncComponentCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popCauseOfSideeffect"))
                {
                    JoinYn = "Y";
                    TabName = "sideeffectCause";
                    ColName = "id";
                    stepIDName = "property_value";
                    dataType = "I";
                } else
                if(popName.equals("popSideeffectResult"))
                {
                    JoinYn = "Y";
                    TabName = "sideeffectResult";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popMeddevItem"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item";
                    ColName = "id";
                    stepIDName = "meddev_item_no";
                }
                if(reporterTypeYN.equals("Y") && a == 0)
                {
                    stepCodeArr[a][0] = JoinYn;
                    stepCodeArr[a][1] = "0";
                    stepCodeArr[a][2] = ColName;
                    stepCodeArr[a][3] = stepID;
                    stepCodeArr[a][4] = stepIDName;
                    stepCodeArr[a][5] = dataType;
                    reporterType = stepID;
                } else
                {
                    stepCodeArr[a][0] = JoinYn;
                    stepCodeArr[a][1] = TabName;
                    stepCodeArr[a][2] = ColName;
                    stepCodeArr[a][3] = stepID;
                    stepCodeArr[a][4] = stepIDName;
                    stepCodeArr[a][5] = dataType;
                }
            }

        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        String reporterTypes = "";
        DataSet ds_reporterTypes = i_xpData.getDataSet("ds_reporterTypes");
        try
        {
            Integer ds_chk = Integer.valueOf(0);
            for(int i = 0; i < ds_reporterTypes.getRowCount(); i++)
            {
                String check_yn = dsGet(ds_reporterTypes, i, "CHECK_YN");
                if(check_yn.equals("1"))
                {
                    ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
                    reporterTypes = (new StringBuilder(String.valueOf(reporterTypes))).append(dsGet(ds_reporterTypes, i, "ID")).append(",").toString();
                }
            }

            if(ds_chk.intValue() > 0)
            {
                reporterTypes = reporterTypes.substring(0, reporterTypes.length() - 1);
                stepCodeArr[curStep.intValue()][0] = "Y";
                stepCodeArr[curStep.intValue()][1] = "reporterTypes";
                stepCodeArr[curStep.intValue()][2] = "id";
                if(reporterType.equals(""))
                    stepCodeArr[curStep.intValue()][3] = reporterTypes;
                else
                    stepCodeArr[curStep.intValue()][3] = reporterType;
                stepCodeArr[curStep.intValue()][4] = "propertyValue";
                stepCodeArr[curStep.intValue()][5] = "L";
            }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        List selectList = null;
        selectList = sideEffectDAO.sideeffectCntStep(stepCodeArr, fmDate, toDate, reportType, curStep);
        mav.addObject("selectList", selectList);
        mav.addObject("curStep", curStep);
        mav.setViewName("/view/jsp/statistics/codeStatisticsStep.jsp");
        return mav;
    }

    public ModelAndView selectSafetySearch(HttpServletRequest request, HttpServletResponse respnse)
        throws PlatformException
    {
        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView();
        PlatformData o_xpData = new PlatformData();
        int nErrorCode = 0;
        String strErrorMsg = "START";
        HttpPlatformRequest pReq = new HttpPlatformRequest(request);
        pReq.receiveData();
        PlatformData i_xpData = pReq.getData();
        VariableList in_vl = i_xpData.getVariableList();
        String fmDate = in_vl.getString("varfmDate");
        String toDate = in_vl.getString("vartoDate");
        String reportType = in_vl.getString("varReportType");
        String step = in_vl.getString("curStep");
        Integer curStep = Integer.valueOf(Integer.parseInt(Function.nullChk(step, "0")));
        DataSet de_select_code = i_xpData.getDataSet("de_select_code");
        String stepCodeArr[][] = new String[curStep.intValue() + 1][6];
        String reporterType = "";
        try
        {
            for(int a = 0; a < curStep.intValue(); a++)
            {
                String popName = dsGet(de_select_code, a, "STEP_CODE");
                String stepID = dsGet(de_select_code, a, "STEP_ID");
                String JoinYn = "";
                String TabName = "";
                String ColName = "";
                String stepIDName = "";
                String dataType = "L";
                String reporterTypeYN = "N";
                if(popName.equals("popReporterType"))
                {
                    JoinYn = "Y";
                    TabName = "reporterTypes";
                    ColName = "id";
                    stepIDName = "propertyValue";
                    reporterTypeYN = "Y";
                } else
                if(popName.equals("popMeaCompany"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item company";
                    ColName = "id";
                    stepIDName = "entp_name";
                } else
                if(popName.equals("popCountry"))
                {
                    JoinYn = "Y";
                    TabName = "country_manufactured";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popMeaClassNo"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item mea_item";
                    ColName = "id";
                    stepIDName = "class_kor_name";
                } else
                if(popName.equals("popItemGrade"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item mea_item categoryGrade";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popCausality"))
                {
                    JoinYn = "Y";
                    TabName = "causality";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popPatient"))
                {
                    JoinYn = "Y";
                    TabName = "patientCodeCondition";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popMedical"))
                {
                    JoinYn = "Y";
                    TabName = "medicalCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popComponent"))
                {
                    JoinYn = "Y";
                    TabName = "juncComponentCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popPatientLevel"))
                {
                    JoinYn = "Y";
                    TabName = "patientCodeCondition";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popMedicalLevel"))
                {
                    JoinYn = "Y";
                    TabName = "medicalCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popComponentLevel"))
                {
                    JoinYn = "Y";
                    TabName = "juncComponentCode";
                    ColName = "id";
                    stepIDName = "fdaSourcePtKor";
                } else
                if(popName.equals("popCauseOfSideeffect"))
                {
                    JoinYn = "Y";
                    TabName = "sideeffectCause";
                    ColName = "id";
                    stepIDName = "property_value";
                    dataType = "I";
                } else
                if(popName.equals("popSideeffectResult"))
                {
                    JoinYn = "Y";
                    TabName = "sideeffectResult";
                    ColName = "id";
                    stepIDName = "propertyValue";
                } else
                if(popName.equals("popMeddevItem"))
                {
                    JoinYn = "Y";
                    TabName = "meb_item";
                    ColName = "id";
                    stepIDName = "meddev_item_no";
                } else
                if(popName.equals("popSideEffectReportType"))
                {
                    JoinYn = "Y";
                    TabName = "report_type";
                    ColName = "id";
                    stepIDName = "propertyValue";
                }
                if(reporterTypeYN.equals("Y"))
                {
                    stepCodeArr[a][0] = JoinYn;
                    stepCodeArr[a][1] = "0";
                    stepCodeArr[a][2] = ColName;
                    stepCodeArr[a][3] = stepID;
                    stepCodeArr[a][4] = stepIDName;
                    stepCodeArr[a][5] = dataType;
                    reporterType = stepID;
                } else
                {
                    stepCodeArr[a][0] = JoinYn;
                    stepCodeArr[a][1] = TabName;
                    stepCodeArr[a][2] = ColName;
                    stepCodeArr[a][3] = stepID;
                    stepCodeArr[a][4] = stepIDName;
                    stepCodeArr[a][5] = dataType;
                }
            }

        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        String reporterTypes = "";
        DataSet ds_reporterTypes = i_xpData.getDataSet("ds_reporterTypes");
        try
        {
            Integer ds_chk = Integer.valueOf(0);
            for(int i = 0; i < ds_reporterTypes.getRowCount(); i++)
            {
                String check_yn = dsGet(ds_reporterTypes, i, "CHECK_YN");
                if(check_yn.equals("1"))
                {
                    ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
                    reporterTypes = (new StringBuilder(String.valueOf(reporterTypes))).append(dsGet(ds_reporterTypes, i, "ID")).append(",").toString();
                }
            }

            if(ds_chk.intValue() > 0)
            {
                reporterTypes = reporterTypes.substring(0, reporterTypes.length() - 1);
                stepCodeArr[curStep.intValue()][0] = "Y";
                stepCodeArr[curStep.intValue()][1] = "reporterTypes";
                stepCodeArr[curStep.intValue()][2] = "id";
                if(reporterType.equals(""))
                    stepCodeArr[curStep.intValue()][3] = reporterTypes;
                else
                    stepCodeArr[curStep.intValue()][3] = reporterType;
                stepCodeArr[curStep.intValue()][4] = "propertyValue";
                stepCodeArr[curStep.intValue()][5] = "L";
            }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        List selectList = null;
        selectList = safetyDAO.safetyCntStep(stepCodeArr, fmDate, toDate, reportType, curStep);
        mav.addObject("selectList", selectList);
        mav.addObject("curStep", curStep);
        mav.setViewName("/view/jsp/statistics/codeStatisticsStep.jsp");
        return mav;
    }

    private MemberDAO memberDAO;
    private PropertiesDAO propertiesDAO;
    private ItemCategoryDAO itemCategoryDAO;
    private SimpleSideeffectReportDAO sideEffectDAO;
    private SafetyDAO safetyDAO;
    private ItemDAO itemDAO;
}
