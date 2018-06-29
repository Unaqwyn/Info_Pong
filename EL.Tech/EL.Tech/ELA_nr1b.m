clear all; clc; format compact; format short g;
%parameter
R=2700;
C=10e-9;
Rl=1000;
dt=1e-1
f_0=10;
f_E=1e6;
N=1e6;
lw=3;
%f_0=0; f_E=10^5; N=10^5; lw=3;
%Funktionen
%g=@(f)0.5*f.*exp(j*2*pi*f/10^5);
g=@(f)1./(1+2*pi*f*1i*C*R);
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%exp data
f_exp_Data=[10,30,100,300,1000,3000,10000,30000,100000,300000];
first_u_Data_RL=[0.15,0.14,0.13,0.13,0.13,0.13,0.12,0.08,0.03,0.01];
first_phi_Data_RL=[0,0,0,0,0,1/18,1/5,1/3,2/5,1/2];
%PLOT
figure(1);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
hold on;
semilogx(f_exp_Data,20*log(first_u_Data_RL*2)/log(10),'linewidth',lw);
hold off;
xlabel('f[Hz]');ylabel('A[dB]');
grid on;
subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
hold on;
semilogx(f_exp_Data,-1*first_phi_Data_RL,'linewidth',lw);
hold off;
xlabel('f[Hz]');ylabel('\phi[\pi]');
grid on;