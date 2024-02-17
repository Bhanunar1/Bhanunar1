#include<iostream>
using namespace std;
void sub(int a,int b)
{
	try
	{
		if(a>b)
		{
			cout<<"a-b:"<<a-b;
		}
		else
		{
			throw a;
		}
	}
	catch(int t)
	{
		cout<<"In catch 1 \n";
		throw a;
	}
}
int main()
{
	int a,b;
	cout<<"\n enter the values of a and b:";
	cin>>a>>b;
	try
	{
		if(a!=b)
		{
			sub(a,b);
		}
		else
		{
			cout<<"both are equal cannot perform subraction\n";
		}
	}
	catch(int t)
	{
		cout<<"In catch 2\n";
		cout<<"value thrown is:"<<t<<endl;
		cout<<"catch rethrown \n";
	}
	return 0;
}
