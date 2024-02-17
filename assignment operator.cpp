#include<iostream>
using namespace std;
class A
{
	private:
		int x;
	public:
		void input()
		{
			cout<<"enter the value of x:";
	        cin>>x;
		}
		void show()
		{
			cout<<"enter the value of x:"<<x;
		}
		void operator=(A);
};
void A::operator=(A a)
{
	x=a.x;
}
int main()
{
	A a1,a2;
	a1.input();
	a2.input();
	a2.operator=(a1);
	a2.show();
	return 0;
}
