package com.drivinggrpc.driving.rpc.proto.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.27.0)",
    comments = "Source: user.proto")
public final class UserRpcServerGrpc {

  private UserRpcServerGrpc() {}

  public static final String SERVICE_NAME = "com.drivinggrpc.driving.rpc.proto.user.UserRpcServer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.loginRequest,
      com.drivinggrpc.driving.rpc.proto.user.loginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = com.drivinggrpc.driving.rpc.proto.user.loginRequest.class,
      responseType = com.drivinggrpc.driving.rpc.proto.user.loginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.loginRequest,
      com.drivinggrpc.driving.rpc.proto.user.loginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.loginRequest, com.drivinggrpc.driving.rpc.proto.user.loginResponse> getLoginMethod;
    if ((getLoginMethod = UserRpcServerGrpc.getLoginMethod) == null) {
      synchronized (UserRpcServerGrpc.class) {
        if ((getLoginMethod = UserRpcServerGrpc.getLoginMethod) == null) {
          UserRpcServerGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.drivinggrpc.driving.rpc.proto.user.loginRequest, com.drivinggrpc.driving.rpc.proto.user.loginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.loginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.loginResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.registerRequest,
      com.drivinggrpc.driving.rpc.proto.user.registerResponse> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = com.drivinggrpc.driving.rpc.proto.user.registerRequest.class,
      responseType = com.drivinggrpc.driving.rpc.proto.user.registerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.registerRequest,
      com.drivinggrpc.driving.rpc.proto.user.registerResponse> getRegisterMethod() {
    io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.registerRequest, com.drivinggrpc.driving.rpc.proto.user.registerResponse> getRegisterMethod;
    if ((getRegisterMethod = UserRpcServerGrpc.getRegisterMethod) == null) {
      synchronized (UserRpcServerGrpc.class) {
        if ((getRegisterMethod = UserRpcServerGrpc.getRegisterMethod) == null) {
          UserRpcServerGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<com.drivinggrpc.driving.rpc.proto.user.registerRequest, com.drivinggrpc.driving.rpc.proto.user.registerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.registerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.registerResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest,
      com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse> getUpPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "upPassword",
      requestType = com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest.class,
      responseType = com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest,
      com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse> getUpPasswordMethod() {
    io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest, com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse> getUpPasswordMethod;
    if ((getUpPasswordMethod = UserRpcServerGrpc.getUpPasswordMethod) == null) {
      synchronized (UserRpcServerGrpc.class) {
        if ((getUpPasswordMethod = UserRpcServerGrpc.getUpPasswordMethod) == null) {
          UserRpcServerGrpc.getUpPasswordMethod = getUpPasswordMethod =
              io.grpc.MethodDescriptor.<com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest, com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "upPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getUpPasswordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest,
      com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse> getGetUserMessageByUserNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserMessageByUserName",
      requestType = com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest.class,
      responseType = com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest,
      com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse> getGetUserMessageByUserNameMethod() {
    io.grpc.MethodDescriptor<com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest, com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse> getGetUserMessageByUserNameMethod;
    if ((getGetUserMessageByUserNameMethod = UserRpcServerGrpc.getGetUserMessageByUserNameMethod) == null) {
      synchronized (UserRpcServerGrpc.class) {
        if ((getGetUserMessageByUserNameMethod = UserRpcServerGrpc.getGetUserMessageByUserNameMethod) == null) {
          UserRpcServerGrpc.getGetUserMessageByUserNameMethod = getGetUserMessageByUserNameMethod =
              io.grpc.MethodDescriptor.<com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest, com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUserMessageByUserName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetUserMessageByUserNameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserRpcServerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserRpcServerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserRpcServerStub>() {
        @java.lang.Override
        public UserRpcServerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserRpcServerStub(channel, callOptions);
        }
      };
    return UserRpcServerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserRpcServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserRpcServerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserRpcServerBlockingStub>() {
        @java.lang.Override
        public UserRpcServerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserRpcServerBlockingStub(channel, callOptions);
        }
      };
    return UserRpcServerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserRpcServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserRpcServerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserRpcServerFutureStub>() {
        @java.lang.Override
        public UserRpcServerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserRpcServerFutureStub(channel, callOptions);
        }
      };
    return UserRpcServerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserRpcServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.drivinggrpc.driving.rpc.proto.user.loginRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.loginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void register(com.drivinggrpc.driving.rpc.proto.user.registerRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.registerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void upPassword(com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpPasswordMethod(), responseObserver);
    }

    /**
     */
    public void getUserMessageByUserName(com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserMessageByUserNameMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drivinggrpc.driving.rpc.proto.user.loginRequest,
                com.drivinggrpc.driving.rpc.proto.user.loginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drivinggrpc.driving.rpc.proto.user.registerRequest,
                com.drivinggrpc.driving.rpc.proto.user.registerResponse>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getUpPasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest,
                com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse>(
                  this, METHODID_UP_PASSWORD)))
          .addMethod(
            getGetUserMessageByUserNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest,
                com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse>(
                  this, METHODID_GET_USER_MESSAGE_BY_USER_NAME)))
          .build();
    }
  }

  /**
   */
  public static final class UserRpcServerStub extends io.grpc.stub.AbstractAsyncStub<UserRpcServerStub> {
    private UserRpcServerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserRpcServerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserRpcServerStub(channel, callOptions);
    }

    /**
     */
    public void login(com.drivinggrpc.driving.rpc.proto.user.loginRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.loginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void register(com.drivinggrpc.driving.rpc.proto.user.registerRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.registerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upPassword(com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpPasswordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserMessageByUserName(com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest request,
        io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserMessageByUserNameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserRpcServerBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserRpcServerBlockingStub> {
    private UserRpcServerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserRpcServerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserRpcServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.drivinggrpc.driving.rpc.proto.user.loginResponse login(com.drivinggrpc.driving.rpc.proto.user.loginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drivinggrpc.driving.rpc.proto.user.registerResponse register(com.drivinggrpc.driving.rpc.proto.user.registerRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse upPassword(com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpPasswordMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse getUserMessageByUserName(com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUserMessageByUserNameMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserRpcServerFutureStub extends io.grpc.stub.AbstractFutureStub<UserRpcServerFutureStub> {
    private UserRpcServerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserRpcServerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserRpcServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drivinggrpc.driving.rpc.proto.user.loginResponse> login(
        com.drivinggrpc.driving.rpc.proto.user.loginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drivinggrpc.driving.rpc.proto.user.registerResponse> register(
        com.drivinggrpc.driving.rpc.proto.user.registerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse> upPassword(
        com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpPasswordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse> getUserMessageByUserName(
        com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserMessageByUserNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_REGISTER = 1;
  private static final int METHODID_UP_PASSWORD = 2;
  private static final int METHODID_GET_USER_MESSAGE_BY_USER_NAME = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserRpcServerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserRpcServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.drivinggrpc.driving.rpc.proto.user.loginRequest) request,
              (io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.loginResponse>) responseObserver);
          break;
        case METHODID_REGISTER:
          serviceImpl.register((com.drivinggrpc.driving.rpc.proto.user.registerRequest) request,
              (io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.registerResponse>) responseObserver);
          break;
        case METHODID_UP_PASSWORD:
          serviceImpl.upPassword((com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest) request,
              (io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse>) responseObserver);
          break;
        case METHODID_GET_USER_MESSAGE_BY_USER_NAME:
          serviceImpl.getUserMessageByUserName((com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserRpcServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getLoginMethod())
              .addMethod(getRegisterMethod())
              .addMethod(getUpPasswordMethod())
              .addMethod(getGetUserMessageByUserNameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
