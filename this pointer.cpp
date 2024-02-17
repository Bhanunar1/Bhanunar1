#include<iostream>
using namespace std;
class Myclass
{
	private:
		int x;
	public:
		void setX(int x)
		{
			this->x=x;
		}
		void printX()
		{
			cout<<"enter the value of x:"<<this->x<<endl;
		}
};
int main()
{
	Myclass obj;
	int value=35;
	obj.setX(value);
	obj.printX();
	return 0;
}
