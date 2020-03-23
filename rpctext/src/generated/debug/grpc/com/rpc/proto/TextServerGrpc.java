package com.rpc.proto;

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
    comments = "Source: text.proto")
public final class TextServerGrpc {

  private TextServerGrpc() {}

  public static final String SERVICE_NAME = "com.rpc.proto.TextServer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.rpc.proto.Request,
      com.rpc.proto.Response> getServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "server",
      requestType = com.rpc.proto.Request.class,
      responseType = com.rpc.proto.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rpc.proto.Request,
      com.rpc.proto.Response> getServerMethod() {
    io.grpc.MethodDescriptor<com.rpc.proto.Request, com.rpc.proto.Response> getServerMethod;
    if ((getServerMethod = TextServerGrpc.getServerMethod) == null) {
      synchronized (TextServerGrpc.class) {
        if ((getServerMethod = TextServerGrpc.getServerMethod) == null) {
          TextServerGrpc.getServerMethod = getServerMethod =
              io.grpc.MethodDescriptor.<com.rpc.proto.Request, com.rpc.proto.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "server"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.proto.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rpc.proto.Response.getDefaultInstance()))
              .setSchemaDescriptor(new TextServerMethodDescriptorSupplier("server"))
              .build();
        }
      }
    }
    return getServerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TextServerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TextServerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TextServerStub>() {
        @java.lang.Override
        public TextServerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TextServerStub(channel, callOptions);
        }
      };
    return TextServerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TextServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TextServerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TextServerBlockingStub>() {
        @java.lang.Override
        public TextServerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TextServerBlockingStub(channel, callOptions);
        }
      };
    return TextServerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TextServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TextServerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TextServerFutureStub>() {
        @java.lang.Override
        public TextServerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TextServerFutureStub(channel, callOptions);
        }
      };
    return TextServerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TextServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void server(com.rpc.proto.Request request,
        io.grpc.stub.StreamObserver<com.rpc.proto.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getServerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getServerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rpc.proto.Request,
                com.rpc.proto.Response>(
                  this, METHODID_SERVER)))
          .build();
    }
  }

  /**
   */
  public static final class TextServerStub extends io.grpc.stub.AbstractAsyncStub<TextServerStub> {
    private TextServerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TextServerStub(channel, callOptions);
    }

    /**
     */
    public void server(com.rpc.proto.Request request,
        io.grpc.stub.StreamObserver<com.rpc.proto.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getServerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TextServerBlockingStub extends io.grpc.stub.AbstractBlockingStub<TextServerBlockingStub> {
    private TextServerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TextServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.rpc.proto.Response server(com.rpc.proto.Request request) {
      return blockingUnaryCall(
          getChannel(), getServerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TextServerFutureStub extends io.grpc.stub.AbstractFutureStub<TextServerFutureStub> {
    private TextServerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TextServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rpc.proto.Response> server(
        com.rpc.proto.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getServerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SERVER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TextServerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TextServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SERVER:
          serviceImpl.server((com.rpc.proto.Request) request,
              (io.grpc.stub.StreamObserver<com.rpc.proto.Response>) responseObserver);
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

  private static abstract class TextServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TextServerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.rpc.proto.TextProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TextServer");
    }
  }

  private static final class TextServerFileDescriptorSupplier
      extends TextServerBaseDescriptorSupplier {
    TextServerFileDescriptorSupplier() {}
  }

  private static final class TextServerMethodDescriptorSupplier
      extends TextServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TextServerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TextServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TextServerFileDescriptorSupplier())
              .addMethod(getServerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
