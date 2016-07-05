// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleBatchJobServiceProvider.java

package system.batch;

import com.google.common.io.ByteStreams;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

// Referenced classes of package system.batch:
//            BatchJobServiceProvider, SimpleBatchJobGroup, BatchJobGroup, BatchJobDAO, 
//            SimpleBatchAdapter, SimpleBatchJobTableType, SimpleBatchJobStatus

public class SimpleBatchJobServiceProvider implements BatchJobServiceProvider {

	public SimpleBatchJobServiceProvider() {
		libraryPath = "C:\\project\\sideEffect2\\WebContent\\WEB-INF\\lib\\excelBatchMan.jar";
		classSignature = "SimpleBatchJobSerivceProvider";
	}

	public SimpleBatchAdapter getBatchAdapter() {
		return batchAdapter;
	}

	public void setBatchAdapter(SimpleBatchAdapter batchAdapter) {
		this.batchAdapter = batchAdapter;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public BatchJobDAO getBatchJobDAO() {
		return batchJobDAO;
	}

	public void setBatchJobDAO(BatchJobDAO batchJobHistoryDAO) {
		batchJobDAO = batchJobHistoryDAO;
	}

	public ModelAndView listBatchJobGroups(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		setDefaultViewSet(mav, request);
		BatchJobGroup sc = new SimpleBatchJobGroup();
		String name = request.getParameter("searchKeyword") != "" ? request.getParameter("searchKeyword") : null;
		sc.setPropertyValue(name);
		long total = batchJobDAO.count(sc);
		List dl = null;
		if (total > 0L) {
			System.out.println((new StringBuilder("nb of found items : ")).append(total).append(", proceeding to list").toString());
			dl = batchJobDAO.list(sc, pg, 10);
		}
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg, "system.do?action=listBatchJobGroups", "", request);
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", "view/style/images/title/sub01_04.gif");
		mav.addObject("titleImage", "view/style/images/title/sign/top_img5.jpg");
		mav.addObject("contentName", "/view/jsp/system/batch/listBatch1.jsp");
		return mav;
	}

	public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/system/systemLeft.jsp");
		mav.addObject("rightName", request.getAttribute("rightName"));
		return mav;
	}

	public ModelAndView createBatchJob(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		String methodSignature = "createBatchJob";
		System.out.println(classSignature + "." + methodSignature + ".status := initialising ");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles("file");
		FileOutputStream fos = null;
		Long tableTypeId = Function.nullChk(request.getParameter("tableTypeId"), -1l);
		System.out.println(classSignature + "." + methodSignature + ".tableTypeId := " + tableTypeId);
		int numberOfFiles = 1;
		for (MultipartFile mpf : files) {
			System.out.println(classSignature + "." + methodSignature + ".numberOfFiles := " + numberOfFiles);
			numberOfFiles++;
			//File f = new File("C:\\hyh\\"+mpf.getOriginalFilename());
			File f = new File(mpf.getOriginalFilename());
			try {
				fos = new FileOutputStream(f);
				fos.write(mpf.getBytes());

				SimpleBatchJobGroup bjg = new SimpleBatchJobGroup();
				bjg.setTableTypeId(tableTypeId);
				bjg.setJobStatus(4l); //작업진행중
				SimpleBatchJobGroup newBatch = (SimpleBatchJobGroup) batchJobDAO.createAndReturn(bjg);
				newBatch = batchJobDAO.read(SimpleBatchJobGroup.class, newBatch.getId());
				SimpleBatchAdapter sba = new SimpleBatchAdapter(libraryPath, f.getAbsolutePath());

				sba.setBatchGroupId(new Long(newBatch.getId()).toString());
				sba.setTmpIdSeqName(newBatch.getTableType().getTmpIdSeqName());
				SimpleBatchJobTableType tableType = batchJobDAO.read(SimpleBatchJobTableType.class, tableTypeId);
				sba.setTargetTableName(tableType.getTempTableName());
				sba.setTableTypeId(tableTypeId);
				System.out.println("before batch job");
				sba.createBatchJob();
				System.out.println("after batch job");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		setDefaultViewSet(mav, request);
		RedirectView rv = new RedirectView("system.do?action=listBatchJobGroups&pg=1");
		mav.setView(rv);
		return mav;
	}

	public ModelAndView createBatchJobPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String methodSignature = "createBatchJobPage";
		SimpleBatchJobTableType sbjtt = new SimpleBatchJobTableType();
		List batchJobTableTypes = batchJobDAO.list(sbjtt, 0, 0);
		setDefaultViewSet(mav, request);
		mav.addObject("tables", batchJobTableTypes);
		mav.addObject("contentName", "/view/jsp/system/batch/createBatch1.jsp");
		return mav;
	}

	public ModelAndView openBatchJobListPopup(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_07.jpg";
		long batchJobGroupId = Function.nullChk(request.getParameter("batchJobGroupId"), -1);
		long tableTypeId = Function.nullChk(request.getParameter("tableTypeId"), -1);
		Long jobStatus = Function.nullChk(request.getParameter("jobStatus"), Long.valueOf(-1L));
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String name = request.getParameter("searchKeyword") != "" ? request.getParameter("searchKeyword") : null;
		System.out.println((new StringBuilder("searchKeyword := ")).append(name).toString());
		long total = 0L;
		List dl = null;
		SimpleBatchJobTableType tableType = (SimpleBatchJobTableType) batchJobDAO.read(SimpleBatchJobTableType.class, tableTypeId);
		Class clazz = null;
		SimpleBatchJobGroup batchJobGroup = null;
		try {
			clazz = Class.forName(tableType.getClassName());
			Constructor ctor = clazz.getConstructor(new Class[0]);
			System.out.println((new StringBuilder("Instantiating ")).append(tableType.getClassName()).toString());
			Object object = ctor.newInstance(new Object[0]);
			Method m = clazz.getMethod("setBatchGroupId", String.class);
			m.invoke(object, new Object[] { Long.valueOf(batchJobGroupId) });
			Method setPropertyValue = clazz.getMethod("setPropertyValue", String.class);
			setPropertyValue.invoke(object, new Object[] { name });
			total = batchJobDAO.count(object);
			if (total > 0L) {
				System.out.println((new StringBuilder("nb of found items : ")).append(total).append(", proceeding to list").toString());
				dl = batchJobDAO.list(object, pg, 10);
				batchJobGroup = (SimpleBatchJobGroup) batchJobDAO.read(SimpleBatchJobGroup.class, batchJobGroupId);
			} else {
				System.out.println((new StringBuilder("nb of found items : ")).append(total).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleBatchJobStatus jobStat = new SimpleBatchJobStatus();
		List jobStats = batchJobDAO.list(jobStat);
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg, "system.do?action=openBatchJobListPopup", "", request);
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("batchJobGroup", batchJobGroup);
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("jobStats", jobStats);
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("popupBatchListContent",
				(new StringBuilder("/view/jsp/system/batch/popupBatchListContent")).append(tableType.getId()).append(".jsp").toString());
		mav.setViewName("/view/jsp/system/batch/popupBatchList.jsp");
		return mav;
	}

	private boolean validateBatchJobApplication(HttpServletRequest request) {
		Long batchGroupId = Function.nullChk(request.getParameter("batchGroupId"), Long.valueOf(-1L));
		String sourceTableName = Function.nullChk(request.getParameter("sourceTableName"));
		String targetTableName = Function.nullChk(request.getParameter("targetTableName"));
		String idColumnName = Function.nullChk(request.getParameter("idColumnName"));
		String idColumnValue = Function.nullChk(request.getParameter("idColumnValue"));
		boolean isValidRequest = false;
		boolean hasBatchGroupId = false;
		boolean hasSourceTableName = false;
		boolean hasTargetTableName = false;
		boolean hasIdColumnName = false;
		boolean hasIdColumnValue = false;
		if (!"".equals(sourceTableName))
			hasSourceTableName = true;
		if (!"".equals(targetTableName))
			hasTargetTableName = true;
		if (!"".equals(idColumnName))
			hasIdColumnName = true;
		if (!"".equals(idColumnValue))
			hasIdColumnValue = true;
		if (batchGroupId.longValue() > 0L)
			hasBatchGroupId = true;
		if (hasSourceTableName && hasTargetTableName && hasIdColumnName && hasIdColumnValue && hasBatchGroupId)
			isValidRequest = true;
		return isValidRequest;
	}

	public ModelAndView applyBatchJob(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Long batchGroupId = Function.nullChk(request.getParameter("batchGroupId"), Long.valueOf(-1L));
		if (batchGroupId != null && batchGroupId.longValue() > 0L) {
			SimpleBatchJobGroup batchGroup = (SimpleBatchJobGroup) batchJobDAO.read(SimpleBatchJobGroup.class, batchGroupId.longValue());
			if (batchGroup != null) {
				if (!isInProgress(batchGroup)) {
					Long tableTypeId = batchGroup.getTableTypeId();
					if (tableTypeId != null && tableTypeId.longValue() > 0L) {
						SimpleBatchJobTableType tableType = (SimpleBatchJobTableType) batchJobDAO.read(SimpleBatchJobTableType.class,
								tableTypeId.longValue());
						if (tableType != null)
							if (tableTypeId.longValue() == 7L) {
								SimpleBatchAdapter sba = new SimpleBatchAdapter(
										"C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\simpleSideEffectDataMover.jar");
								sba.setSourceTableName(tableType.getTempTableName());
								sba.setTargetTableName(tableType.getTableName());
								sba.setIdColumnName("id");
								sba.setIdColumnValue(tableType.getIdSequenceName());
								sba.setBatchGroupId(batchGroupId.toString());
								sba.setMode(Integer.valueOf(2));
								sba.moveSideeffect();
							} else if (tableTypeId.longValue() == 8L) {
								SimpleBatchAdapter sba = new SimpleBatchAdapter(
										"C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\safetyInfoExcelManager.jar");
								sba.setSourceTableName(tableType.getTempTableName());
								sba.setTargetTableName(tableType.getTableName());
								sba.setIdColumnName("id");
								sba.setIdColumnValue(tableType.getIdSequenceName());
								sba.setBatchGroupId(batchGroupId.toString());
								sba.setMode(Integer.valueOf(2));
								sba.setCommand("move");
								sba.apply();
							} else if (tableTypeId.longValue() == 5L) {
								libraryPath = "C:\\SGI_Project\\eclipseWorkspace\\sideEffect2\\WebContent\\WEB-INF\\lib\\simpleCompanyBatchManager.jar";
								SimpleBatchAdapter sba = new SimpleBatchAdapter(libraryPath);
								sba.setBatchGroupId(batchGroupId.toString());
								sba.setTableTypeId(tableTypeId);
								sba.apply();
							} else {
								SimpleBatchAdapter sba = new SimpleBatchAdapter(libraryPath);
								sba.setSourceTableName(tableType.getTempTableName());
								sba.setTargetTableName(tableType.getTableName());
								sba.setIdColumnName("id");
								sba.setIdColumnValue(tableType.getIdSequenceName());
								sba.setBatchGroupId(batchGroupId.toString());
								sba.setMode(Integer.valueOf(1));
								sba.setCommand("move");
								sba.apply();
							}
					}
				}
			} else {
				System.out.println((new StringBuilder("found null batch group with group id ")).append(batchGroupId).toString());
			}
		}
		setDefaultViewSet(mav, request);
		mav.addObject("contentName", "/view/jsp/system/batch/createBatch1.jsp");
		return mav;
	}

	public ModelAndView downloadFormat(HttpServletRequest request, HttpServletResponse response) {
		int formatId = Function.nullChk(request.getParameter("formatId"), 0);
		if (formatId > 0) {
			SimpleBatchJobTableType tableType = (SimpleBatchJobTableType) batchJobDAO.read(SimpleBatchJobTableType.class, formatId);
			File f = new File((new StringBuilder(String.valueOf(request.getRealPath("/")))).append(tableType.getTemplateFile()).toString());
			response.setHeader("Content-Transfer-Encoding:", "binary");
			response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(f.getName()).toString());
			response.setHeader("Content-Length", (new StringBuilder()).append(f.length()).toString());
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			try {
				InputStream is = new FileInputStream(f);
				ByteStreams.copy(is, response.getOutputStream());
				response.flushBuffer();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean isInProgress(SimpleBatchJobGroup batchGroup) {
		boolean isInProgress = true;
		if (batchGroup != null && (batchGroup.getMoveJobStatus() == null || batchGroup.getMoveJobStatus().longValue() != 1L))
			isInProgress = false;
		return isInProgress;
	}

	private BatchJobDAO batchJobDAO;
	private HibernateTemplate hibernateTemplate;
	private SimpleBatchAdapter batchAdapter;
	private String libraryPath;
	private String classSignature;
}
