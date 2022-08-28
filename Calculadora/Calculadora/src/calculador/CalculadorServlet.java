package calculador;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcular")
public class CalculadorServlet extends HttpServlet {
	
	private int dia;
	private int mes;
	private int ano;
	
	private String idadePorExtenso(int dia, int mes, int ano) {
		
		LocalDate localDate1 = LocalDate.now();

		int diaAtual = localDate1.getDayOfMonth();
		int mesAtual = localDate1.getMonthValue();
		int anoAtual = localDate1.getYear();

		int anos = anoAtual - ano;
		int meses = mesAtual - mes;
		int dias = diaAtual - dia;
		
		if (meses < 0) {
			anos = (anos - 1);
			meses = (meses + 12);		
		}
		
		if (dias < 0) {
			meses = (meses - 1);
			dias = (dias+ 30);		
		}
		

		String idadePorExtenso = anos + " anos, " + meses + " meses, e " + dias + " dias ";
		return idadePorExtenso; 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");

		try{			
			PrintWriter out = response.getWriter();
			String nome = request.getParameter("nome");
			int dia, mes, ano;

			try {
				dia = Integer.valueOf(request.getParameter("dia"));
				mes = Integer.valueOf(request.getParameter("mes"));
				ano = Integer.valueOf(request.getParameter("ano"));

				out.println("<html><body>");
				out.println("<h1>Olá, " + nome + "!<br/>Você tem " + idadePorExtenso(dia, mes, ano) + "</h1>");
				out.println("</html></body>");
				}
				catch(ArithmeticException ae){
					ae.printStackTrace();
					out.println("<html><body><h1> Erro na conversão de algum dos valores. </h1></body></html>");
				}
			
			out.close();
			} 
			
			catch (IOException e){
				e.printStackTrace();
		}
	}
	
	
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}


	private static final long serialVersionUID = 1L;
  
    public CalculadorServlet() {
        super();        
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}	

}
