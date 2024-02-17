#include<iostream>
using namespace std;
class vector
{
	private:
		int x,y ,z;
	public:
		void input()
		{
		cout<<"enter the values:";
		cin>>x>>y>>z;
    	}
		void equation()
		{
	      cout<<"equation is formed:";
		}
	    void display()
	    {
	     std::cout<<x<<"x+"<<y<<"y+"<<z<<"z"<<std::endl;
		}
};
int main()
{
	vector v;
	v.input();
	v.equation();
	v.display();
	return 0;
}
