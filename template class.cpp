#include<iostream>
using namespace std;
template<class T>
class sample
{
	T K;
	public:
		void get()
		{
			cout<<"enter the value of x:";
			cin>>K;
		}
		void show()
		{
			cout<<"enter the value of x:"<<K<<endl;		
		}
};
int main()
{
	sample<int>S;
	sample<float>S1;
	sample<char>S2;
	S.get();
	S1.get();
	S2.get();
	S.show();
	S1.show();
	S2.show();
	return 0;
}
