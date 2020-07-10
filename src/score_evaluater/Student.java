package score_evaluater;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
import java.util.Comparator;

public class Student {

	private String ID;
	private double[] score=new double[4];
	private double total_score=0;
	private double avg_score=0;
	private String gpa="";
	
	
	//建構子
	public  Student(String ID,double[] a)
	{
		this.ID=ID;
		this.score[0]=a[0];
		this.score[1]=a[1];
		this.score[2]=a[2];
		this.score[3]=a[3];
		
	}
	
	//回傳學生ID
	public String getId()
	{
		return this.ID;
	}
	
	//回傳學生分數
	public double[] getStuScore()
	{
		return this.score;
	}
	
	
	//計算總分
	public void setStuTtlScore()
	{
		this.total_score+=(this.score[0]*0.4)+(this.score[1]*0.2)+(this.score[2]*0.2)+(this.score[3]*0.2);
	}
	
	//回傳總分
	public double getStuTtlScore()
	{
		return this.total_score;
	}
	
	//計算平均分
	public void setStuAvgScore()
	{
		double total_original_score=0;
		total_original_score+=this.score[0]+this.score[1]+this.score[2]+this.score[3];
		int count=0;
		for(int i=0;i<=3;i++)
		{
			if(score[i]!=0)
			{
				count++;
			}
		}
		if(count==0)
		{
			this.avg_score=0;
		}
		else
		this.avg_score+=total_original_score/count;
		
	}
	
	//回傳平均分
	public double getStuAvgScore()
	{
		return this.avg_score;
	}
	
	//計算GPA
	public void setStuGpa()
	{
		if(this.total_score<60)
		{
			this.gpa+="F";
		}
		else if(this.total_score>=60&&this.total_score<70)
		{
			this.gpa+="D";
		}
		else if(this.total_score>=70&&this.total_score<72)
		{
			this.gpa+="C-";
		}
		else if(this.total_score>=72&&this.total_score<78)
		{
			this.gpa+="C";
		}
		else if(this.total_score>=78&&this.total_score<80)
		{
			this.gpa+="C+";
		}
		else if(this.total_score>=80&&this.total_score<82)
		{
			this.gpa+="B-";
		}
		else if(this.total_score>=82&&this.total_score<88)
		{
			this.gpa+="B";
		}
		else if(this.total_score>=88&&this.total_score<90)
		{
			this.gpa+="B+";
		}
		else if(this.total_score>=90&&this.total_score<92)
		{
			this.gpa+="A-";
		}
		else if(this.total_score>=92&&this.total_score<98)
		{
			this.gpa+="A";
		}
		else if(this.total_score>=98)
		{
			this.gpa+="A+";
		}
		else
		{
			this.gpa+="Error on GPA";
		}
	}
	
	//回傳GPA
	public String getStuGpa()
	{
		return this.gpa;
	}
	
	public  static void gradeDistribution(ArrayList<Student> student_score)
	{
		String dis="";
		int a1=0,a2=0,a3=0,b1=0,b2=0,b3=0,c1=0,c2=0,c3=0,d=0,f=0;
		Student students;
		for(int i=0;i<student_score.size();i++)
		{
			students=student_score.get(i);
			if(students.gpa.equals("F"))
			{
				f++;
			}
			else if(students.gpa.equals("D"))
			{
				d++;
			}
			else if(students.gpa.equals("C-"))
			{
				c3++;
			}
			else if(students.gpa.equals("C"))
			{
				c2++;
			}
			else if(students.gpa.equals("C+"))
			{
				c1++;
			}
			else if(students.gpa.equals("B-"))
			{
				b3++;
			}
			else if(students.gpa.equals("B"))
			{
				b2++;
			}
			else if(students.gpa.equals("B+"))
			{
				b1++;
			}
			else if(students.gpa.equals("A-"))
			{
				a3++;
			}
			else if(students.gpa.equals("A"))
			{
				a2++;
			}
			else if(students.gpa.equals("A+"))
			{
				a1++;
			}
		}
		
		dis+="A+=\t"+a1+"\n"+"A =\t"+a2+"\n"+"A-=\t"+a3+"\n"+"B+=\t"+b1+"\n"+"B =\t"+b2+"\n"+"B-=\t"+b3+"\n"+"C+=\t"+c1+"\n"+"C =\t"+c2+"\n"+"C-=\t"+c3+"\n"+"D =\t"+d+"\n"+"F =\t"+f+"\n";
		System.out.println(dis);
	}

	public static double[] allStuAvgScore(ArrayList<Student> student_score)
	{
		Student students;
		double[] all_avg_score=new double[4];
		all_avg_score[0]=0;
		all_avg_score[1]=0;
		all_avg_score[2]=0;
		all_avg_score[3]=0;
		
		for(int i=0;i<student_score.size();i++)
		{
			students=student_score.get(i);
			all_avg_score[0]+=students.score[0];
			all_avg_score[1]+=students.score[1];
			all_avg_score[2]+=students.score[2];
			all_avg_score[3]+=students.score[3];
		}
		
		all_avg_score[0]/=student_score.size();
		all_avg_score[1]/=student_score.size();
		all_avg_score[2]/=student_score.size();
		all_avg_score[3]/=student_score.size();
		
		return all_avg_score;
	}
	public static double[] allStuStd(ArrayList<Student> student_score)
	{
		Student students;
		double[] all_avg_score=new double[4];
		all_avg_score[0]=0;
		all_avg_score[1]=0;
		all_avg_score[2]=0;
		all_avg_score[3]=0;
		double[] all_stu_std=new double[4];
		double[] single_stu_std=new double[4];
		
		//計算標準值sigma
		for(int i=0;i<student_score.size();i++)
		{
			students=student_score.get(i);
			all_avg_score[0]+=students.score[0];
			all_avg_score[1]+=students.score[1];
			all_avg_score[2]+=students.score[2];
			all_avg_score[3]+=students.score[3];
		}
		all_avg_score[0]/=student_score.size();
		all_avg_score[1]/=student_score.size();
		all_avg_score[2]/=student_score.size();
		all_avg_score[3]/=student_score.size();
		
		//計算標準差
		for(int i=0;i<student_score.size();i++)
		{
			students=student_score.get(i);
			students.score[0]-=all_avg_score[0];
			students.score[1]-=all_avg_score[1];
			students.score[2]-=all_avg_score[2];
			students.score[3]-=all_avg_score[3];
			
			students.score[0]=Math.pow(students.score[0], 2);
			students.score[1]=Math.pow(students.score[1], 2);
			students.score[2]=Math.pow(students.score[2], 2);
			students.score[3]=Math.pow(students.score[3], 2);
			
			all_stu_std[0]+=students.score[0];
			all_stu_std[1]+=students.score[1];
			all_stu_std[2]+=students.score[2];
			all_stu_std[3]+=students.score[3];
		}
		all_stu_std[0]/=student_score.size();
		all_stu_std[1]/=student_score.size();
		all_stu_std[2]/=student_score.size();
		all_stu_std[3]/=student_score.size();
		
		all_stu_std[0]=Math.sqrt(all_stu_std[0]);
		all_stu_std[1]=Math.sqrt(all_stu_std[1]);
		all_stu_std[2]=Math.sqrt(all_stu_std[2]);
		all_stu_std[3]=Math.sqrt(all_stu_std[3]);
		
		return all_stu_std;
		
	}
	
	static Comparator comparator_midterm = new Comparator<Student>()
	{
		public int compare(Student student1, Student student2)
		{
			if(student1.score[0]<student2.score[0])
            {
            	return 1;
            } 
			else if(student1.score[0]==student2.score[0])
			{
				int i=student1.ID.compareTo(student2.ID);
				if(i>0)
				{
					return 1;
				}
				else
				{
					return -1;
				}
			}
            else
            {
            	return -1;
            }
		}
    };  
    
    static Comparator comparator_preQuiz = new Comparator<Student>()
	{
		public int compare(Student student1, Student student2)
		{
			if(student1.score[1]<student2.score[1])
            {
            	return 1;
            } 
			else if(student1.score[1]==student2.score[1])
			{
				int i=student1.ID.compareTo(student2.ID);
				if(i>0)
				{
					return 1;
				}
				else
				{
					return -1;
				}
			}
            else
            {
            	return -1;
            }
		}
    };  
    
    static Comparator comparator_labQuiz = new Comparator<Student>()
	{
		public int compare(Student student1, Student student2)
		{
			if(student1.score[2]<student2.score[2])
            {
            	return 1;
            }  
            else if(student1.score[2]==student2.score[2])
            {
            	int i=student1.ID.compareTo(student2.ID);
				if(i>0)
				{
					return 1;
				}
				else
				{
					return -1;
				}
            }
            else
            {
            	return 1;
            }
		}
    };  
    
    static Comparator comparator_HW1 = new Comparator<Student>()
	{
		public int compare(Student student1, Student student2)
		{
			if(student1.score[3]<student2.score[3])
            {
            	return 1;
            } 
			else if(student1.score[3]==student2.score[3])
			{
				int i=student1.ID.compareTo(student2.ID);
				if(i>0)
				{
					return 1;
				}
				else
				{
					return -1;
				}
			}
            else
            {
            	return -1;
            }
		}
    };  
	
	public static ArrayList scoreSorting(ArrayList<Student> student_score,String a)
	{
		ArrayList students=new ArrayList(student_score);
		if(a.equals("midterm"))
		{
			Collections.sort(students,comparator_midterm);
			return students;
		}
		else if(a.equals("prequiz"))
		{
			Collections.sort(students,comparator_preQuiz);
			return students;
		}
		else if(a.equals("labquiz"))
		{
			Collections.sort(students,comparator_labQuiz);
			return students;
		}
		else if(a.equals("hw1"))
		{
			Collections.sort(students,comparator_HW1);
			return students;
		}
		else
		{
			return students;
		}
	}
	
}
