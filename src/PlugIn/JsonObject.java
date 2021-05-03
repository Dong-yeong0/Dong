package PlugIn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/jsonObject")
public class JsonObject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JsonObject() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject outObj = new JSONObject();
		JSONArray fAry = new JSONArray();
		fAry.add("Reading");
		fAry.add("Sleeping");
		JSONObject fObj = new JSONObject();
		fObj.put("cat","mew");
		fObj.put("dog","mang");
		outObj.put("name","hong");
		outObj.put("hobby",fAry);
		outObj.put("pet",fObj);
		response.getWriter().print(outObj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
